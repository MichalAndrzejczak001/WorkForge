package spring.project.workforge.offerpreparation.offer.client.chatgpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatGPTWebClientConfig {

    @Bean
    public WebClient chatGPTWebClient(
            WebClient.Builder builder,
            @Value("${chatgpt.base-url}") String baseUrl
    ) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }
}