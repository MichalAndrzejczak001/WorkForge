package spring.project.workforge.offerpreparation.offer.client.chatgpt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ChatGptWebClient {

    private final WebClient webClient;

    public String improveText(String text) {
        return webClient.post()
                .uri("/improve-text")
                .bodyValue(new ImproveTextRequest(text))
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException(
                                        "ChatGPT API error: " + body))
                )
                .bodyToMono(ChatGPTApiResponse.class)
                .timeout(Duration.ofSeconds(5))
                .map(ChatGPTApiResponse::text)
                .block();
    }

    public String findGaps(String text) {
        return webClient.post()
                .uri("/find-gaps")
                .bodyValue(new ImproveTextRequest(text))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException(
                                        "ChatGPT API error: " + body))
                )
                .bodyToMono(ChatGPTApiResponse.class)
                .timeout(Duration.ofSeconds(5))
                .map(ChatGPTApiResponse::text)
                .block();
    }
}
