package spring.project.workforge.offerpreparation.offer.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import spring.project.workforge.offerpreparation.offer.model.enums.PromotionType;

import java.time.LocalDateTime;

public record PromotionCreateRequest(
        @NotNull(message = "Active flag is required")
        Boolean active,

        @NotNull(message = "Promotion start date is required")
        @FutureOrPresent(message = "Promotion start date must be in the present or future")
        LocalDateTime startDate,

        @NotNull(message = "Promotion end date is required")
        @Future(message = "Promotion end date must be in the future")
        LocalDateTime endDate,

        @NotNull(message = "Promotion type is required")
        PromotionType promotionType
) {
}
