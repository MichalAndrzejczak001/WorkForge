package spring.project.workforge.offerpreparation.offer.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SalaryRangeRequest (
        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @Digits(integer = 10, fraction = 2)
        BigDecimal min,
        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @Digits(integer = 10, fraction = 2)
        BigDecimal max,
        @NotBlank
        @Size(min = 3, max = 3)
        String currency
) {
}
