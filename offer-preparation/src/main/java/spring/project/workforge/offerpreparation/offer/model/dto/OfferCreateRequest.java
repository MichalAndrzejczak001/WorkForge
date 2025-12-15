package spring.project.workforge.offerpreparation.offer.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import spring.project.workforge.offerpreparation.location.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.time.LocalDateTime;
import java.util.List;

public record OfferCreateRequest(
        @NotBlank(message = "Title cannot be empty.")
        @Size(max = 50, message = "Title must be at most 100 characters.")
        String title,
        @NotBlank(message = "Recruiter name cannot be empty.")
        @Size(max = 50, message = "Recruiter name must be at most 100 charcters.")
        String recruiter,
        @NotBlank(message = "Company name cannot be empty.")
        @Size(max = 50, message = "Company name must be at most 150 characters")
        String company,
        @NotBlank(message = "Description cannot be empty")
        @Size(max = 1000000, message = "Description must be at most 2000 characters")
        String description,
        @NotNull(message = "Work type is required")
        WorkType workType,
        @NotNull(message = "Tags list is required")
        @Size(min = 1, message = "Tags must contain at least 1 tag.")
        List<@NotBlank(message = "Tag cannot be blank") @Size(max = 30, message = "Tag must be at most 30 characters long.") String> tags,
        @Size(min = 0, max = 2, message = "Salary must contain exactly 2 values: [min, max]")
        List<@PositiveOrZero(message = "Salary must be zero or positive") Double> salary,
        @NotNull(message = "Experience is required")
        @PositiveOrZero(message = "Experience must be zero or positive")
        Double experience,
        @NotNull(message = "Location is required")
        @Valid
        LocationRequest location,
        @NotNull(message = "Start date is required")
        LocalDateTime startDate,
        @NotNull(message = "End date is required")
        LocalDateTime endDate
) {}

