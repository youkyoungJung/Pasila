package org.ssafy.pasila.global.infra.redis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.global.infra.redis.entity.ChatRedis;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public ChatRedis findByLiveId(String key) throws JsonProcessingException {
        if(key == null) return null;

        String value = (String) redisTemplate.opsForValue().get("chat:" + key);

        if(value == null || value.isEmpty()) return null;
        return objectMapper.readValue(value, ChatRedis.class);
    }

    public List<ChatRedis> recentChatList(String key) {
        ListOperations<String, Object> chatList = redisTemplate.opsForList();
        return null;
    }

    public void saveChat(String key, ChatRedis chat) throws JsonProcessingException {
        redisTemplate.opsForList().rightPush(key, objectMapper.writeValueAsString(chat));
    }
}
