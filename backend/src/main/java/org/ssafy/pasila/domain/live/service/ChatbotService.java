package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.dto.request.ChatbotRequestDto;
import org.ssafy.pasila.domain.live.entity.Chatbot;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.ChatbotQueryRepository;
import org.ssafy.pasila.domain.live.repository.ChatbotRepository;
import org.ssafy.pasila.domain.live.repository.LiveQueryRepository;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.service.ProductService;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.Message;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final ChatbotRepository chatbotRepository;

    private final GptClient gptClient;

    private final ProductService productService;

    private final LiveRepository liveRepository;

    private final ChatbotQueryRepository chatbotQueryRepository;

    public List<Chatbot> save(List<Chatbot> chatbotList) {
        return chatbotRepository.saveAll(chatbotList);
    }

    public List<Chatbot> findByLiveId(String liveId) {
        return chatbotRepository.findAllByLive_id(liveId);
    }

    public String chatbotMessage(ChatbotRequestDto chatbotRequestDto) {
        String liveId = chatbotRequestDto.getLiveId();
        String message = chatbotRequestDto.getMessage();

        Live live = liveRepository.findById(liveId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        List<Chatbot> chatbots = findByLiveId(liveId);
        List<Message> chatbotMessages = new ArrayList<>();

        for(Chatbot chatbot: chatbots) {
            chatbotMessages.addAll(chatbot.createMessage());
        }

        return gptClient.chatbotMessage(live.getProduct(), message, chatbotMessages);
    }

    public void updateChatbot(String liveId, List<Chatbot> chatbotList) {
        chatbotQueryRepository.deleteAllByLiveId(liveId);
        save(chatbotList);
    }
}
