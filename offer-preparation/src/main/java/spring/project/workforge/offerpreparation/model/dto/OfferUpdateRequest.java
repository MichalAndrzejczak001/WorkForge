package spring.project.workforge.offerpreparation.model.dto;

import spring.project.workforge.offerpreparation.model.enums.WorkType;

import java.util.List;

public record OfferUpdateRequest(
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

