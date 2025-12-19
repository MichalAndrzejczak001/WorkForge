package spring.project.workforge.offerpreparation.offer.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponsePayment;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.offer.service.OfferService;

import java.net.URI;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);

    @GetMapping
    public ResponseEntity<List<OfferResponse>> findOffers(@RequestParam(required = false) String title) {
        logger.debug("Fetching offers, title filter: {}", title);
        List<OfferResponse> offers = title == null ? offerService.findAll() : offerService.findByTitle(title);
        logger.debug("Returning {} offers", offers.size());
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> findById(@PathVariable @Positive Long id) {
        logger.debug("Fetching offer by id {}", id);
        OfferResponse offer = offerService.findById(id);
        logger.debug("Found offer: {}", id);
        return ResponseEntity.ok(offer);
    }

    @PostMapping
    public ResponseEntity<OfferResponsePayment> createOffer(
            @Valid @RequestBody OfferCreateRequest offer) {
        logger.debug("Creating offer with title: {}", offer.title());
        OfferResponsePayment created = offerService.createOffer(offer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getOfferResponse().id())
                .toUri();
        logger.info("Offer created with id: {}", created.getOfferResponse().id());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable @Positive Long id,
            @Valid @RequestBody OfferUpdateRequest offer
    ) {
        logger.debug("Updating offer with id: {} with data: {}", id, offer);
        OfferResponse updated = offerService.updateOffer(id, offer);
        logger.info("Offer updated: {}", updated.id());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable @Positive Long id) {
        logger.debug("Deleting offer with id: {}", id);
        offerService.deleteOffer(id);
        logger.info("Offer deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}