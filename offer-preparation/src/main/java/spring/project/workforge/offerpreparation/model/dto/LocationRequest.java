package spring.project.workforge.offerpreparation.model.dto;

public record LocationRequest(
        Long id,
        String name,
        String address,
        Double latitude,
        Double longitude
) {}
