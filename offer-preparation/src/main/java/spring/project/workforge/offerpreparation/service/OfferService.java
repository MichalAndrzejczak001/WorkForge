package spring.project.workforge.offerpreparation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.project.workforge.offerpreparation.mappers.OfferMapper;
import spring.project.workforge.offerpreparation.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.model.entity.Offer;
import spring.project.workforge.offerpreparation.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.repository.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OfferService implements IOfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    public List<OfferResponse> findAll() {
        return offerMapper.toResponseList(offerRepository.findAll());
    }

    @Override
    public OfferResponse findById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toResponse)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));
    }

    @Override
    public List<OfferResponse> findByTitle(String title) {
        List<Offer> offers = offerRepository.findByTitle(title);
        if (offers.isEmpty()) {
            throw new OfferNotFoundException("No offers found with title: " + title);
        }
        return offers.stream()
                .map(offerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OfferResponse createOffer(OfferCreateRequest offer) {
        Offer newOffer = offerMapper.toEntity(offer);
        Offer savedOffer = offerRepository.save(newOffer);
        return offerMapper.toResponse(savedOffer);
    }

    @Override
    public OfferResponse updateOffer(Long id, OfferUpdateRequest offer) {
        Offer offerToUpdate = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));

        offerMapper.updateEntityFromRequest(offer, offerToUpdate);

        Offer savedOffer = offerRepository.save(offerToUpdate);

        return offerMapper.toResponse(savedOffer);
    }

    @Override
    public void deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + "doesn't exist."));
        offerRepository.delete(offer);
    }
}
