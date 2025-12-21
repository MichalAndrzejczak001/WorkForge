package spring.project.workforge.offerpreparation.offer.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.project.workforge.offerpreparation.offer.client.ChatGPTResponse;
import spring.project.workforge.offerpreparation.offer.service.ChatGPTService;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/offers")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;
    private static final Logger logger = LoggerFactory.getLogger(ChatGPTController.class);

    @PostMapping("/{id}/chatgpt/stylistics")
    public ResponseEntity<ChatGPTResponse> getCorrectedOfferDescription(@PathVariable @Positive Long id) {
        logger.debug("Generating stylistic correction for offer id {}", id);
        ChatGPTResponse chatGPTResponse = chatGPTService.getCorrectedOfferDescription(id);
        logger.info("Stylistic correction generated for offer id {}", id);
        return ResponseEntity.ok(chatGPTResponse);
    }

    @PostMapping("/{id}/chatgpt/find-gaps")
    public ResponseEntity<ChatGPTResponse> getGapsInOfferDescription(@PathVariable @Positive Long id) {
        logger.debug("Finding gaps for offer description with id {}", id);
        ChatGPTResponse chatGPTResponse = chatGPTService.getGapsInOfferDescription(id);
        logger.info("Gaps found for offer description with id {}", id);
        return ResponseEntity.ok(chatGPTResponse);
    }
}
