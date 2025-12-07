package spring.project.workforge.payment.kafak;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    private PaymentProducer paymentProducer;

    @Autowired
    public KafkaController(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @GetMapping("/sendMessage")
    public void sendMessage() {
        paymentProducer.sendPaymentMessage("This is kafka message.");
    }
}
