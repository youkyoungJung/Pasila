package org.ssafy.pasila.global.infra.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.global.infra.redis.entity.Live;
import org.ssafy.pasila.global.infra.redis.repository.LiveRedisRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class LiveRedisService {

    private final LiveRedisRepository liveRedisRepository;

    public String addLive(String liveId, String title){
        Live live = new Live(liveId, title, 0);
        liveRedisRepository.save(live);
        log.info("createLive 에 왔는가? ");

        return live.getId();
    }


    @Transactional
    public int addLike(String liveId){
        Live live = liveRedisRepository.findById(liveId)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        live.addLike();
        liveRedisRepository.save(live);
        return live.getLikeCnt();
    }

    public int getLikeCnt(String liveId){
        Live live = liveRedisRepository.findById(liveId)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        return live.getLikeCnt();
    }

}
