package spring.project.workforge.offerpreparation.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.workforge.offerpreparation.location.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.location.model.entity.Location;
import spring.project.workforge.offerpreparation.location.repository.LocationRepository;
import spring.project.workforge.offerpreparation.offer.client.PaymentPayload;
import spring.project.workforge.offerpreparation.offer.client.PaymentWebClient;
import spring.project.workforge.offerpreparation.offer.mappers.OfferMapper;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponsePayment;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.enums.IsPaid;
import spring.project.workforge.offerpreparation.offer.model.enums.Status;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferInvalidDateException;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final LocationRepository locationRepository;
    private final OfferMapper offerMapper;

    @Transactional(readOnly = true)
    @Override
    public List<OfferResponse> findAll() {
        return offerMapper.toResponseList(offerRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferResponse> findByTitle(String title) {
        List<Offer> offers = offerRepository.findByTitle(title);
        if (offers.isEmpty()) {
            throw new OfferNotFoundException("No offers found with title: " + title);
        }
        return offers.stream()
                .map(offerMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public OfferResponse findById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toResponse)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));
    }

    @Transactional
    @Override
    public OfferResponsePayment createOffer(OfferCreateRequest request) {
        Location location = findOrCreateLocation(request.location());
        Offer offer = offerMapper.toEntity(request);
        offer.setLocation(location);

        checkDateCorrectness(offer);
        setStatus(offer);
        offer.setIsPaid(IsPaid.DRAFT);

        Offer savedOffer = offerRepository.save(offer);
        OfferResponse offerResponse = offerMapper.toResponse(savedOffer);

        String paymentUrl = createPayment(savedOffer.getId(), 20000L, "Payment for job offfer");

        return new OfferResponsePayment(paymentUrl, offerResponse);
    }

    private String createPayment(Long id, Long amount, String description) {
        PaymentWebClient paymentWebClient = new PaymentWebClient("http://payment-service:80");
        PaymentPayload paymentPayload = new PaymentPayload(id, amount, description);

        return paymentWebClient.sendPaymentRequest("/api/v1/payments/create-payment", paymentPayload);

    }

    @Transactional
    @Override
    public OfferResponse updateOffer(Long id, OfferUpdateRequest request) {
        Location location = findOrCreateLocation(request.location());
        Offer offerToUpdate = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));
        offerMapper.updateEntityFromRequest(request, offerToUpdate);
        offerToUpdate.setLocation(location);

        checkDateCorrectness(offerToUpdate);
        setStatus(offerToUpdate);

        Offer savedOffer = offerRepository.save(offerToUpdate);
        OfferResponse offerResponse = offerMapper.toResponse(savedOffer);

        return offerResponse;
    }

    private void checkDateCorrectness(Offer offer) {
        if(offer.getStartDate().isAfter(offer.getEndDate())) {
            throw new OfferInvalidDateException("Start date " +  offer.getStartDate() + " is after end date " + offer.getEndDate());
        }
        if(offer.getStartDate().isBefore(LocalDateTime.now())) {
            throw new OfferInvalidDateException("Start date " +  offer.getStartDate() + " is earlier than current date " + LocalDateTime.now());
        }
        if((ChronoUnit.YEARS.between(offer.getStartDate(), offer.getEndDate()) > 1)) {
            throw new OfferInvalidDateException("End date " + offer.getEndDate() + " is later than year after start date + " + offer.getStartDate());
        }
    }

    private void setStatus(Offer offer) {
        if(offer.getStartDate().isBefore(LocalDateTime.now())) {
            offer.setStatus(Status.BEFORE);
        } else if (!offer.getStartDate().isBefore((LocalDateTime.now())) && offer.getEndDate().isAfter(LocalDateTime.now())) {
            offer.setStatus(Status.ACTIVE);
        } else {
            offer.setStatus(Status.AFTER);
        }
    }

    @Override
    public void deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + "doesn't exist."));

        Location loc = offer.getLocation();

        offerRepository.delete(offer);

        if(loc != null) {
            locationRepository.delete(loc);
        }
    }

    private Location findOrCreateLocation(LocationRequest request) {
        return locationRepository.findByLatitudeAndLongitude(request.latitude(), request.longitude())
                .orElseGet(() -> locationRepository.save(
                        new Location(null, request.name(), request.address(), request.latitude(), request.longitude())
                ));
    }
}
