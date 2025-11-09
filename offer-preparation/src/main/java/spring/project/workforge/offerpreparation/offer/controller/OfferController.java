package spring.project.workforge.offerpreparation.offer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferUpdateRequest;
import spring.project.workforge.offerpreparation.offer.service.OfferService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<List<OfferResponse>> findOffers(@RequestParam(required = false) String title) {
        if (title != null) {
            return ResponseEntity.ok(offerService.findByTitle(title));
        }
        return ResponseEntity.ok(offerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestBody OfferCreateRequest offer) {
        OfferResponse created = offerService.createOffer(offer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse> updateOffer(@PathVariable Long id, @RequestBody OfferUpdateRequest offer) {
        return ResponseEntity.ok(offerService.updateOffer(id, offer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }
}
