package spring.project.workforge.offerpreparation.offer.model.dto;

import spring.project.workforge.offerpreparation.offer.model.enums.PromotionType;

import java.time.LocalDateTime;

public record PromotionResponse (
        Boolean active,
        LocalDateTime startDate,
        LocalDateTime endDate,
        PromotionType promotionType
) {
}
