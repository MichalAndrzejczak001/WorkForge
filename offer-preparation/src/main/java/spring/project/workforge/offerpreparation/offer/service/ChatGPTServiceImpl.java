package spring.project.workforge.offerpreparation.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.project.workforge.offerpreparation.offer.client.chatgpt.ChatGPTResponse;
import spring.project.workforge.offerpreparation.offer.client.chatgpt.ChatGptWebClient;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;

@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    private final OfferRepository offerRepository;
    private final ChatGptWebClient chatGPTWebClient;

    @Override
    public ChatGPTResponse getCorrectedOfferDescription(Long id) {
        Offer offer = getOffer(id);

        String description = validateDescription(offer);

        String corrected = chatGPTWebClient.improveText(description);

        return new ChatGPTResponse(id, corrected);
    }

    @Override
    public ChatGPTResponse getGapsInOfferDescription(Long id) {
        Offer offer = getOffer(id);

        String description = validateDescription(offer);

        String gaps = chatGPTWebClient.findGaps(description);

        return new ChatGPTResponse(id, gaps);
    }

    private Offer getOffer(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() ->
                        new OfferNotFoundException("Offer with id " + id + " not found"));
    }

    private String validateDescription(Offer offer) {
        String description = offer.getDescription();

        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Offer description is empty");
        }
        return description;
    }
}
