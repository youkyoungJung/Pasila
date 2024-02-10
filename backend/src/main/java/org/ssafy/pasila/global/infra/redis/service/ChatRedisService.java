package org.ssafy.pasila.global.infra.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.global.infra.redis.entity.ChatRedis;
import org.ssafy.pasila.global.infra.redis.repository.ChatRedisRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRedisService {
    private final ChatRedisRepository chatRedisRepository;

    @Transactional
    public void saveChat(ChatRedis chat) {

        try {
            chatRedisRepository.saveChat(chat.getLiveId(), chat);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

    }

    @Transactional
    public String getChatList(String liveId) {

        return chatRedisRepository.recentChat(liveId);

    }

}
