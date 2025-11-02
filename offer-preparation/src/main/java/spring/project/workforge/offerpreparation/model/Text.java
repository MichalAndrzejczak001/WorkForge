package spring.project.workforge.offerpreparation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "text")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
}
