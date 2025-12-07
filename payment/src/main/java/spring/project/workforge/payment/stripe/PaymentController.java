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
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://twoja-domena.pl/sukces")
                .setCancelUrl("https://twoja-domena.pl/anulowano")
                .putMetadata("offerId", payload.getId().toString()) // <-- metadata w sesji
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("pln")
                                                .setUnitAmount(payload.getAmount())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(payload.getDescription())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);

        Map<String, String> sessionMetadata = session.getMetadata();
        System.out.println("Session metadata: " + sessionMetadata);

        return Map.of("url", session.getUrl());
    }
}