package spring.project.workforge.offerpreparation.offer.service;

import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionCreateRequest;

public interface OfferPromotionService {
    OfferResponse createOfferPromotion(Long id, PromotionCreateRequest promotion);

    void deleteOfferPromotion(Long id);
}
