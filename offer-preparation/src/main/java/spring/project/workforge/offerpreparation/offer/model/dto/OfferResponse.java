package spring.project.workforge.offerpreparation.offer.model.dto;

import spring.project.workforge.offerpreparation.location.model.dto.LocationResponse;
import spring.project.workforge.offerpreparation.location.model.entity.Location;
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
        LocationResponse location,
        String description,
        WorkType workType,
        List<String> tags,
        SalaryRangeResponse salaryRangeResponse,
        Double experience,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Status status,
        IsPaid isPaid
) {}
