package spring.project.workforge.offerpreparation.offer.model.dto;

import spring.project.workforge.offerpreparation.location.model.dto.LocationResponse;
import spring.project.workforge.offerpreparation.offer.model.enums.IsPaid;
import spring.project.workforge.offerpreparation.offer.model.enums.Status;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.time.LocalDateTime;
import java.util.List;

public record OfferResponse(
        Long id,
        String title,
        String recruiter,
        String company,
        String description,
        WorkType workType,
        List<String> tags,
        List<Double> salary,
        Double experience,
        LocationResponse location,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Status status,
        IsPaid isPaid
) {}
