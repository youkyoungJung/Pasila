package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveRepository;


import static java.time.LocalDateTime.*;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final RedisTemplate<String, String> redisTemplate;

    private final LiveRepository liveRepository;

    public int joinLive(String liveId, String memberId) {

        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("participant : " + liveId, memberId);
        return setOperations.size("participant : " + liveId).intValue();

    }

    public int exitLive(String liveId, String memberId) {

        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.remove("participant : " + liveId, memberId);
        return setOperations.size("participant : " + liveId).intValue();

    }

    @Transactional
    public void updateLiveOn(String liveId) {
        Live live = getLiveById(liveId);
        live.setLiveOnAt(now());
        live.setOn(true);
    }

    @Transactional
    public void updateLiveOff(String liveId) {
        Live live = getLiveById(liveId);
        live.setLiveOffAt(now());
        live.setOn(false);
        live.setFullVideoUrl(null);
    }

    public Live getLiveById(String id) {
        return liveRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
    }

}
