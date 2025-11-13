package spring.project.workforge.payment.stripe;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
//        Stripe.apiKey = secretKey;
        Stripe.apiKey = "sk_test_51SQPI7DkFpKnagwD2SXHCKH3JK7qO1anB8g3E5RFRaF5zLADusw9CcN7guL1NT70TjLLMHiGTu9zQGFZFW7wVTnr005AKOpwV2";
        System.out.println("Stripe key loaded: " + secretKey);
    }
}
