package spring.project.workforge.payment.stripe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPayload {
    private Long id;
    private Long amount;
    private String description;
}
