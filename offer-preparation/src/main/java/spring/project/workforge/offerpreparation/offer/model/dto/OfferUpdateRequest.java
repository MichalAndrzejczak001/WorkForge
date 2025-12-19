package spring.project.workforge.offerpreparation.offer.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import spring.project.workforge.offerpreparation.location.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.time.LocalDateTime;
import java.util.List;

public record OfferUpdateRequest(
        @NotBlank(message = "Title cannot be empty.")
        @Size(max = 50, message = "Title must be at most 100 characters.")
        String title,
        @NotBlank(message = "Recruiter name cannot be empty.")
        @Size(max = 50, message = "Recruiter name must be at most 100 charcters.")
        String recruiter,
        @NotBlank(message = "Company name cannot be empty.")
        @Size(max = 50, message = "Company name must be at most 150 characters")
        String company,
        @NotNull(message = "Location is required")
        @Valid
        LocationRequest location,
        @NotBlank(message = "Description cannot be empty")
        @Size(max = 10_000, message = "Description must be at most 2000 characters")
        String description,
        @NotNull(message = "Work type is required")
        WorkType workType,
        @NotEmpty(message = "Tags list is required")
        List<@NotBlank(message = "Tag cannot be blank") @Size(max = 30, message = "Tag must be at most 30 characters long.") String> tags,
        @NotNull
        @Valid
        SalaryRangeRequest salaryRangeRequest,
        @NotNull(message = "Experience is required")
        @PositiveOrZero(message = "Experience must be zero or positive")
        Double experience,
        @NotNull(message = "Start date is required")
        LocalDateTime startDate,
        @NotNull(message = "End date is required")
        LocalDateTime endDate
) {}

