package spring.project.workforge.offerpreparation.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.workforge.offerpreparation.model.enums.WorkType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    // TODO: create entity responsible for compoany and recruiter

    private String recruiter;
    private String company;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String description;

    //flags
    private WorkType workType;

    //tags
    private List<String> tags;

    //numbers
    private List<Double> salary;
    private Double experience;
}
