package spring.project.workforge.offerpreparation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.project.workforge.offerpreparation.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.model.entity.Offer;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface OfferMapper {

    // Request -> Encja (np. przy zapisie nowej oferty)
    @Mapping(target = "id", ignore = true) // ID generuje baza danych
    Offer toEntity(OfferCreateRequest request);

    Offer toEntity(OfferUpdateRequest request);

    // Encja -> Response (np. przy pobieraniu oferty)
    OfferResponse toResponse(Offer offer);

    // Dodatkowo: lista encji -> lista DTO
    List<OfferResponse> toResponseList(List<Offer> offers);

    void updateEntityFromRequest(OfferUpdateRequest request, @MappingTarget Offer offer);
}
