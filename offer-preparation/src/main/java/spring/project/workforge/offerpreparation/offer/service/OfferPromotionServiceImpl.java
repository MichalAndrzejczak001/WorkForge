package spring.project.workforge.offerpreparation.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.workforge.offerpreparation.offer.mappers.OfferMapper;
import spring.project.workforge.offerpreparation.offer.mappers.PromotionMapper;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.entity.Promotion;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferInvalidDateException;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.offer.model.excepitons.PromotionInvalidDateException;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class OfferPromotionServiceImpl implements OfferPromotionService{
    private final OfferRepository offerRepository;
    private final PromotionMapper promotionMapper;
    private final OfferMapper offerMapper;

    @Transactional
    @Override
    public OfferResponse createOfferPromotion(Long id, PromotionCreateRequest promotion) {
        Promotion createdPromotion = promotionMapper.toEntity(promotion);
        checkDateCorrectness(createdPromotion);
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));
        offer.setPromotion(createdPromotion);
        Offer createdOffer = offerRepository.save(offer);
        OfferResponse offerResponse = offerMapper.toResponse(createdOffer);
        return offerResponse;
    }

    private void checkDateCorrectness(Promotion promotion) {
        if(promotion.getStartDate().isAfter(promotion.getEndDate())) {
            throw new PromotionInvalidDateException("Start date " +  promotion.getStartDate() + " is after end date " + promotion.getEndDate());
        }
        if(promotion.getStartDate().isBefore(LocalDateTime.now())) {
            throw new PromotionInvalidDateException("Start date " +  promotion.getStartDate() + " is earlier than current date " + LocalDateTime.now());
        }
        if(promotion.getEndDate().isAfter(promotion.getStartDate().plusMonths(1))) {
            throw new PromotionInvalidDateException("End date " + promotion.getEndDate() + " is later than month after start date + " + promotion.getStartDate());
        }
    }

    @Transactional
    @Override
    public void deleteOfferPromotion(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " doesn't exist."));
        offer.setPromotion(null);
        offerRepository.save(offer);
    }
}
