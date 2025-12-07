package spring.project.workforge.payment.stripe;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.project.workforge.payment.stripe.kafka.PaymentProducer;

@RestController
@RequestMapping("/api/v1/payments")
public class StripeWebhookController {

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;
    private PaymentProducer paymentProducer;

    @Autowired
    public StripeWebhookController(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signatureHeader) {

        Event event;

        try {
            event = Webhook.constructEvent(
                    payload,
                    signatureHeader,
                    webhookSecret
            );
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        switch (event.getType()) {

            case "checkout.session.completed":
                Session session = (Session) event.getDataObjectDeserializer()
                        .getObject().orElseThrow();

                // pobieramy metadata
                String sessionMetadataId = session.getMetadata().get("offerId");

                //TODO: Zmień na loggera
                System.out.println("CHECKOUT COMPLETED - metadata.id = " + sessionMetadataId);
                paymentProducer.sendPaymentMessage(sessionMetadataId);
                break;

            default:
                //TODO: Zmień na loggera
                System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("success");
    }
}

