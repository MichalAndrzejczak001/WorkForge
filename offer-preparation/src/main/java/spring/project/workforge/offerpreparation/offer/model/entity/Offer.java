package spring.project.workforge.offerpreparation.offer.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import spring.project.workforge.offerpreparation.location.model.entity.Location;
import spring.project.workforge.offerpreparation.offer.model.enums.IsPaid;
import spring.project.workforge.offerpreparation.offer.model.enums.Status;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String title;
    // TODO: create entity responsible for compoany and recruiter

    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String recruiter;
    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotBlank
    @Size(max = 10_000)
    @Lob
    private String description;

    //flags
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType workType;

    //tags
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "offer_tags", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "tag", nullable = false, length = 100)
    private List<@NotBlank @Size(max = 30) String> tags;

    //numbers
    @Valid
    @Embedded
    private SalaryRange salary;
    @NotNull
    @PositiveOrZero
    private Double experience;

    //date
    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDateTime startDate;
    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDateTime endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsPaid isPaid;
}
