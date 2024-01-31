package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.entity.ChatLog;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChattingService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveChat(ChatLog chatLog) {

        ValueOperations<String , String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append(chatLog.getLiveId() ,
                " " + chatLog.getMemberId() +
                " " + chatLog.getMessage() +
                " " + chatLog.getCreatedAt());
    }
}


