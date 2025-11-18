package spring.project.workforge.offerpreparation.offer.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.enums.Status;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OfferStatusScheduler {
    private final OfferRepository offerRepository;;
    
    @Scheduled (fixedRate = 60_000)
    public void updateStatuses(){
        List<Offer> offers = offerRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Offer offer : offers) {
            if(offer.getStartDate().isAfter(now)) {
                offer.setStatus(Status.BEFORE);
            } else if (offer.getStartDate().isBefore(now) && offer.getEndDate().isAfter(now)) {
                offer.setStatus(Status.ACTIVE);
            } else {
                offer.setStatus(Status.AFTER);
            }
        }

        offerRepository.saveAll(offers);
    }

}
