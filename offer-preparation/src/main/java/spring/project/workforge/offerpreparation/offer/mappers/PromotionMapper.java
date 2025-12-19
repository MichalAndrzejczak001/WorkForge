package spring.project.workforge.offerpreparation.offer.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionResponse;
import spring.project.workforge.offerpreparation.offer.model.entity.Promotion;

@Component
public class PromotionMapper {
    public Promotion toEntity(PromotionCreateRequest request) {
        if (request == null) {
            return null;
        }

        Promotion promotion = new Promotion();

        promotion.setActive(request.active());
        promotion.setStartDate(request.startDate());
        promotion.setEndDate(request.endDate());
        promotion.setPromotionType(request.promotionType());

        return promotion;
    }

    public PromotionResponse toPromotionResponse(Promotion promotion) {
        if (promotion == null) {
            return null;
        }

        return new PromotionResponse(
                promotion.getActive(),
                promotion.getStartDate(),
                promotion.getEndDate(),
                promotion.getPromotionType()
        );
    }
}
