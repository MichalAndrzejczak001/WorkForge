package spring.project.workforge.offerpreparation.location.model.dto;

public record LocationRequest(
        String name,
        String address,
        Double latitude,
        Double longitude
) {}
