package spring.project.workforge.offerpreparation.offer.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.workforge.offerpreparation.location.model.entity.Location;
import spring.project.workforge.offerpreparation.offer.model.enums.IsPaid;
import spring.project.workforge.offerpreparation.offer.model.enums.Status;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.time.LocalDateTime;
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

    //date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private IsPaid isPaid;
}
