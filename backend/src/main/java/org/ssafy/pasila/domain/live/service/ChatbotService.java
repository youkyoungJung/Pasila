package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.dto.request.ChatbotRequestDto;
import org.ssafy.pasila.domain.live.entity.Chatbot;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.ChatbotQueryRepository;
import org.ssafy.pasila.domain.live.repository.ChatbotRepository;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.Message;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final ChatbotRepository chatbotRepository;

    private final GptClient gptClient;

    private final LiveRepository liveRepository;

    private final ChatbotQueryRepository chatbotQueryRepository;

    @Transactional
    public List<Chatbot> save(List<Chatbot> chatbotList, String liveId) {
        if (liveId == null) {
            throw new RestApiException(ErrorCode.RESOURCE_NOT_FOUND);
        }

        Live live = liveRepository.findById(liveId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        for(Chatbot bot : chatbotList){
            bot.setLive(live);
        }
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

    @Transactional
    public void updateChatbot(String liveId, List<Chatbot> chatbotList) {
        chatbotQueryRepository.deleteAllByLiveId(liveId);
        save(chatbotList, liveId);
    }
}
