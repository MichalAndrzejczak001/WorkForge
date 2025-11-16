package spring.project.workforge.payment.stripe;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class StripeWebhookController {

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

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

                // 👉 tutaj zrobisz cokolwiek chcesz po płatności
                System.out.println("MESSAGE:CHECKOUT COMPLETED: " + session.getId());
                break;

            case "payment_intent.succeeded":
                PaymentIntent intent = (PaymentIntent) event.getDataObjectDeserializer()
                        .getObject().orElseThrow();

                System.out.println("MESSAGE:PAYMENT SUCCEEDED: " + intent.getId());
                break;

            default:
                System.out.println("MESSAGE:Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("success");
    }
}

