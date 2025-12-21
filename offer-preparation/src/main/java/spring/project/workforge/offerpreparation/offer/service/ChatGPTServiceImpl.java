package spring.project.workforge.offerpreparation.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.project.workforge.offerpreparation.offer.client.ChatGPTResponse;
import spring.project.workforge.offerpreparation.offer.client.ChatGPTWebClient;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;
import spring.project.workforge.offerpreparation.offer.model.excepitons.OfferNotFoundException;
import spring.project.workforge.offerpreparation.offer.repository.OfferRepository;

@RequiredArgsConstructor
@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    private final OfferRepository offerRepository;

    @Override
    public ChatGPTResponse getCorrectedOfferDescription(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " not found"));

        String description = offer.getDescription();

        String correctedDescription = sendChatGPTQuestion(description);

        ChatGPTResponse chatGPTResponse = new ChatGPTResponse(id, correctedDescription);

        return chatGPTResponse;
    }

    private String sendChatGPTQuestion(String description) {
        ChatGPTWebClient chatGPTWebClient = new ChatGPTWebClient("http://fastapi-chatgpt-service:80");

        return chatGPTWebClient.getCorrectedOfferDescription(description);
    }

    @Override
    public ChatGPTResponse getGapsInOfferDescription(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " not found"));

        String description = offer.getDescription();

        String gaps = sendChatGPTQuestionForGaps(description);

        ChatGPTResponse chatGPTResponse = new ChatGPTResponse(id, gaps);

        return chatGPTResponse;
    }

    private String sendChatGPTQuestionForGaps(String description) {
        ChatGPTWebClient chatGPTWebClient = new ChatGPTWebClient("http://fastapi-chatgpt-service:80");

        return chatGPTWebClient.getGapsFromOfferDescription(description);
    }
}
