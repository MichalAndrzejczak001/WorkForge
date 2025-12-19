package spring.project.workforge.offerpreparation.offer.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.PromotionResponse;
import spring.project.workforge.offerpreparation.offer.service.OfferPromotionService;

import java.net.URI;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offers")
public class OfferPromotionController {

    private final OfferPromotionService offerPromotionService;
    private static final Logger logger = LoggerFactory.getLogger(OfferPromotionController.class);

    @PostMapping("/{id}/promotion")
    public ResponseEntity<OfferResponse> createOfferPromotion(
            @PathVariable @Positive Long id,
            @Valid @RequestBody PromotionCreateRequest promotion
    ) {
        logger.debug("Creating promotion for offer with id {}", id);
        OfferResponse offerResponse = offerPromotionService.createOfferPromotion(id, promotion);
        logger.info("Promotion created for offer with id: {}", id);
        return ResponseEntity.ok(offerResponse);
    }

    @DeleteMapping("/{id}/promotion")
    public ResponseEntity<Void> deleteOfferPromotion(@PathVariable @Positive Long id) {
        logger.debug("Deleting promotion for offer with id: {}", id);
        offerPromotionService.deleteOfferPromotion(id);
        logger.info("Promotion deleted for offer with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
