package spring.project.workforge.offerpreparation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.project.workforge.offerpreparation.model.dto.LocationRequest;
import spring.project.workforge.offerpreparation.model.dto.LocationResponse;
import spring.project.workforge.offerpreparation.model.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    // Request -> Encja (np. przy tworzeniu oferty)
    @Mapping(target = "id", ignore = true)
    Location toEntity(LocationRequest request);

    // Encja -> Response (np. przy zwracaniu oferty)
    LocationResponse toResponse(Location location);
}
