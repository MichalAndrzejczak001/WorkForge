package spring.project.workforge.offerpreparation.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OfferConsumer {

    @KafkaListener(topics = "test-topic", groupId = "offer-prep-group")
    public void consume(String message) {
        System.out.println("Otrzymano wiadomość: " + message);
        // tutaj możesz wywołać logikę przygotowania oferty
    }
}
