package spring.project.workforge.offerpreparation.location.model.dto;

public record LocationResponse(
        Long id,
        String name,
        String address,
        Double latitude,
        Double longitude
) {}
