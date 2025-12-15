package spring.project.workforge.offerpreparation.offer.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.location.mapper.LocationMapper;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OfferMapper {

    private final LocationMapper locationMapper;

    // Tworzenie nowej encji Offer z DTO
    public Offer toEntity(OfferCreateRequest request) {
        if (request == null) return null;
        Offer offer = new Offer();
        offer.setTitle(request.title());
        offer.setRecruiter(request.recruiter());
        offer.setCompany(request.company());
        offer.setDescription(request.description());
        offer.setWorkType(request.workType());
        offer.setTags(request.tags());
        offer.setSalary(request.salary());
        offer.setExperience(request.experience());
        offer.setLocation(locationMapper.toEntity(request.location()));
        offer.setStartDate(request.startDate());
        offer.setEndDate(request.endDate());
        return offer;
    }

    // Aktualizacja istniejącej encji Offer z DTO
    public void updateEntityFromRequest(OfferUpdateRequest request, Offer offer) {
        if (request == null || offer == null) return;
        offer.setTitle(request.title());
        offer.setRecruiter(request.recruiter());
        offer.setCompany(request.company());
        offer.setDescription(request.description());
        offer.setWorkType(request.workType());
        offer.setTags(request.tags());
        offer.setSalary(request.salary());
        offer.setExperience(request.experience());
        offer.setLocation(locationMapper.toEntity(request.location()));
        offer.setStartDate(request.startDate());
        offer.setEndDate(request.endDate());
    }

    // Encja -> DTO
    public OfferResponse toResponse(Offer offer) {
        if (offer == null) return null;
        return new OfferResponse(
                offer.getId(),
                offer.getTitle(),
                offer.getRecruiter(),
                offer.getCompany(),
                offer.getDescription(),
                offer.getWorkType(),
                offer.getTags(),
                offer.getSalary(),
                offer.getExperience(),
                locationMapper.toResponse(offer.getLocation()
                ),
                offer.getStartDate(),
                offer.getEndDate(),
                offer.getStatus(),
                offer.getIsPaid()
        );
    }

    // Lista encji -> lista DTO
    public List<OfferResponse> toResponseList(List<Offer> offers) {
        if (offers == null) return null;
        return offers.stream()
                .map(this::toResponse)
                .toList();
    }
}
