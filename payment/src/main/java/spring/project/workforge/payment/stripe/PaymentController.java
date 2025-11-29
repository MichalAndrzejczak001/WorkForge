package spring.project.workforge.payment.stripe;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;
import spring.project.workforge.payment.stripe.model.dto.PaymentPayload;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @PostMapping("/create-payment")
    public Map<String, String> createCheckoutSession(@RequestBody PaymentPayload payload) throws Exception {
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://twoja-domena.pl/sukces")
                        .setCancelUrl("https://twoja-domena.pl/anulowano")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("pln")
                                                        .setUnitAmount(payload.getAmount())  // cena w groszach
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName(payload.getDescription()) // opis/nazwa produktu
                                                                        .putMetadata("offerId", payload.getId().toString()) // ID w metadanych
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Session session = Session.create(params);

        params.getLineItems().forEach(item -> {
            Map<String, String> metadata = item.getPriceData().getProductData().getMetadata();
            System.out.println("Line item metadata: " + metadata);
        });

        return Map.of("url", session.getUrl());
    }
}