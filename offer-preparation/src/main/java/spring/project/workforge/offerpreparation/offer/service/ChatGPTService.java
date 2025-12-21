package spring.project.workforge.offerpreparation.offer.service;

import spring.project.workforge.offerpreparation.offer.client.chatgpt.ChatGPTResponse;

public interface ChatGPTService {
    ChatGPTResponse getCorrectedOfferDescription(Long id);

    ChatGPTResponse getGapsInOfferDescription(Long id);
}
