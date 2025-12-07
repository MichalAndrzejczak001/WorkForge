package spring.project.workforge.offerpreparation.offer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.enums.IsPaid;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;
import spring.project.workforge.offerpreparation.offer.service.IOfferService;

@Service
public class OfferConsumer {

    private OfferRepository offerRepository;

    @Autowired
    public OfferConsumer(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @KafkaListener(topics = "payment-topic", groupId = "offer-prep-group")
    public void consume(String id) {
        Offer existingOffer = offerRepository.findById(Long.parseLong(id))
                .orElseThrow(() ->  new OfferNotFoundException("Offer with id " +  id + " not found."));

        existingOffer.setIsPaid(IsPaid.PAID);

        offerRepository.save(existingOffer);
    }
}
