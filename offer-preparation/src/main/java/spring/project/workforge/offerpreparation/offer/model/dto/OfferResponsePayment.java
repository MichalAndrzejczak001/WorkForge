package spring.project.workforge.offerpreparation.offer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponsePayment {
    private String url;
    private OfferResponse offerResponse;
}
