package spring.project.workforge.offerpreparation.offer.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ChatGPTWebClient {

    private final WebClient webClient;

    public ChatGPTWebClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public String getCorrectedOfferDescription(String text) {
        // Tworzymy payload
        Map<String, String> payload = Map.of("text", text);

        // Wysyłamy POST do FastAPI i odbieramy Mapę
        Mono<Map> responseMono = webClient.post()
                .uri("/improve-text")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> response = responseMono.block(); // blokujemy wątek i czekamy na odpowiedź

        // Zwracamy poprawiony tekst
        return (String) response.get("text");
    }

    public String getGapsFromOfferDescription(String text) {
        // Tworzymy payload
        Map<String, String> payload = Map.of("text", text);

        // Wysyłamy POST do FastAPI i odbieramy Mapę
        Mono<Map> responseMono = webClient.post()
                .uri("/find-gaps")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> response = responseMono.block(); // blokujemy wątek i czekamy na odpowiedź

        // Zwracamy poprawiony tekst
        return (String) response.get("text");
    }
}
