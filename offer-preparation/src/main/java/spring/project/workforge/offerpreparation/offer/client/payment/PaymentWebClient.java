package spring.project.workforge.offerpreparation.offer.client.payment;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentWebClient {
    private final WebClient webClient;

    public PaymentWebClient(String mainUrl) {
        webClient = WebClient.create(mainUrl);
    }

    public String sendPaymentRequest(String uri, PaymentPayload paymentPayload) {

        Mono<Map> responseMono = webClient.post()
                .uri(uri)
                .bodyValue(paymentPayload)
                .retrieve()
                .bodyToMono(Map.class); // odbieramy odpowiedź jako Map<String,String>

        Map<String, String> response = responseMono.block();

        return response.get("url");
}
}

