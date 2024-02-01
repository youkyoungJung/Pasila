package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final RedisTemplate<String, String> redisTemplate;

    public int joinLive(String liveId , String memberId) {

        SetOperations<String , String> setOperations = redisTemplate.opsForSet();
        setOperations.add("participant : " + liveId , memberId);
        return  setOperations.size("participant : " + liveId).intValue();

    }

    public int exitLive(String liveId ,String memberId) {

        SetOperations<String , String> setOperations = redisTemplate.opsForSet();
        setOperations.remove("participant : " + liveId , memberId);
        return  setOperations.size("participant : " + liveId).intValue();

    }

}
