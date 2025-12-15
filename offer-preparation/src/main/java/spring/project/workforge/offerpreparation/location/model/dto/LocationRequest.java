package spring.project.workforge.offerpreparation.location.model.dto;

import jakarta.validation.constraints.*;

public record LocationRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 50, message = "Name must have at most 50 characters")
        String name,

        @NotBlank(message =  "Address cannot be blank")
        @Size(max = 500, message = "Address must have at most 500 characters")
        String address,

        @NotNull(message = "Latitude cannot be null")
        @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be between -90 and 90")
        Double latitude,

        @NotNull(message = "Longitude cannot be null")
        @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be between -180 and 180")
        Double longitude
) {}
