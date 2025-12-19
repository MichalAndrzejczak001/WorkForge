package spring.project.workforge.offerpreparation.offer.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.location.mapper.LocationMapper;
import spring.project.workforge.offerpreparation.offer.model.dto.*;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.entity.SalaryRange;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OfferMapper {

    private final LocationMapper locationMapper;
    private final SalaryRangeMapper salaryRangeMapper;
    private final PromotionMapper promotionMapper;

    /* ===================== CREATE ===================== */

    public Offer toEntity(OfferCreateRequest request) {
        if (request == null) {
            return null;
        }

        Offer offer = new Offer();
        mapCommonFields(request, offer);

        return offer;
    }

    /* ===================== UPDATE ===================== */

    public void updateEntityFromRequest(OfferUpdateRequest request, Offer offer) {
        if (request == null || offer == null) {
            return;
        }

        mapCommonFields(request, offer);
    }

    /* ===================== RESPONSE ===================== */

    public OfferResponse toResponse(Offer offer) {
        if (offer == null) {
            return null;
        }

        return new OfferResponse(
                offer.getId(),
                offer.getTitle(),
                offer.getRecruiter(),
                offer.getCompany(),
                locationMapper.toResponse(offer.getLocation()),
                offer.getDescription(),
                offer.getWorkType(),
                offer.getTags(),
                salaryRangeMapper.toSalaryRangeResponse(offer.getSalary()),
                offer.getExperience(),
                offer.getStartDate(),
                offer.getEndDate(),
                offer.getStatus(),
                offer.getIsPaid(),
                promotionMapper.toPromotionResponse(offer.getPromotion())
        );
    }

    public List<OfferResponse> toResponseList(List<Offer> offers) {
        if (offers == null) {
            return List.of();
        }

        return offers.stream()
                .map(this::toResponse)
                .toList();
    }

    /* ===================== SHARED LOGIC ===================== */

    private void mapCommonFields(
            OfferCreateRequest request,
            Offer offer
    ) {
        offer.setTitle(request.title());
        offer.setRecruiter(request.recruiter());
        offer.setCompany(request.company());
        offer.setDescription(request.description());
        offer.setWorkType(request.workType());
        offer.setTags(request.tags());
        offer.setExperience(request.experience());
        offer.setStartDate(request.startDate());
        offer.setEndDate(request.endDate());

        offer.setLocation(locationMapper.toEntity(request.location()));
        offer.setSalary(salaryRangeMapper.toSalaryRange(request.salaryRangeRequest()));
    }

    private void mapCommonFields(
            OfferUpdateRequest request,
            Offer offer
    ) {
        offer.setTitle(request.title());
        offer.setRecruiter(request.recruiter());
        offer.setCompany(request.company());
        offer.setDescription(request.description());
        offer.setWorkType(request.workType());
        offer.setTags(request.tags());
        offer.setExperience(request.experience());
        offer.setStartDate(request.startDate());
        offer.setEndDate(request.endDate());

        offer.setLocation(locationMapper.toEntity(request.location()));
        offer.setSalary(salaryRangeMapper.toSalaryRange(request.salaryRangeRequest()));
    }

}
