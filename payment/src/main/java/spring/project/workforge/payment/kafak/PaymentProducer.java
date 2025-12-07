package spring.project.workforge.payment.kafak;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentMessage(String message) {
        System.out.println("Sending Payment Message to Kafka: " + message);
        kafkaTemplate.send("test-topic", message);
    }
}
