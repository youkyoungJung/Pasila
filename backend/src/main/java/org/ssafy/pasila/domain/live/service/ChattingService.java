package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.dto.ChatLogDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChattingService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveChat(ChatLogDto chatLog) {
        ValueOperations<String , String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append("chatlog : " + chatLog.getLiveId() ,
                " " + chatLog.getMemberId() +
                " " + chatLog.getMessage() +
                " " + chatLog.getCreatedAt());
    }

    public void delChat(String LiveId){
        redisTemplate.delete("chatlog : " + LiveId);
    }

}


