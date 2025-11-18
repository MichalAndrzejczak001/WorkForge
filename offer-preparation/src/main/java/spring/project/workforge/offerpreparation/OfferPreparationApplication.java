package spring.project.workforge.offerpreparation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OfferPreparationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferPreparationApplication.class, args);
    }

}
