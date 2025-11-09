package spring.project.workforge.offerpreparation.offer.model.dto;

import spring.project.workforge.offerpreparation.location.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.offer.model.enums.WorkType;

import java.util.List;

public record OfferCreateRequest(
        String title,
        String recruiter,
        String company,
        String description,
        WorkType workType,
        List<String> tags,
        List<Double> salary,
        Double experience,
        LocationRequest location
) {}

