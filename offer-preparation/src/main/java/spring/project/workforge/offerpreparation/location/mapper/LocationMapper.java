package spring.project.workforge.offerpreparation.location.mapper;

import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.location.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.location.model.dto.LocationResponse;
import spring.project.workforge.offerpreparation.location.model.entity.Location;

@Component
public class LocationMapper {

    // DTO -> encja
    public Location toEntity(LocationRequest request) {
        if (request == null) return null;
        Location location = new Location();
        location.setName(request.name());
        location.setAddress(request.address());
        location.setLatitude(request.latitude());
        location.setLongitude(request.longitude());
        return location;
    }

    // Encja -> DTO
    public LocationResponse toResponse(Location location) {
        if (location == null) return null;
        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude()
        );
    }
}
