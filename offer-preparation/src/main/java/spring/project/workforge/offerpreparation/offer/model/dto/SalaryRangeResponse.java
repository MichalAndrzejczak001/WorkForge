package spring.project.workforge.offerpreparation.offer.model.dto;

import java.math.BigDecimal;

public record SalaryRangeResponse (
        BigDecimal min,
        BigDecimal max,
        String currency
) {
}
