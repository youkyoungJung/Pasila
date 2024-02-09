package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.dto.request.CreateLiveRequestDto;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductRepository;


import static java.time.LocalDateTime.*;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final RedisTemplate<String, String> redisTemplate;

    private final LiveRepository liveRepository;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

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
    public void updateLiveOff(String liveId, String fullVideoUrl, int likeCnt) {
        Live live = getLiveById(liveId);
        live.setLiveOffAt(now());
        live.setOn(false);
        live.setFullVideoUrl(fullVideoUrl);
        live.setLikeCnt(likeCnt);
    }

    public Live getLiveById(String id) {
        return liveRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public String getProductId(String liveId) {
        Live live = liveRepository.findById(liveId).orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        return live.getProduct().getId();
    }

    @Transactional
    public String saveLive(CreateLiveRequestDto createLiveRequestDto, Long memberId, String productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        Live live = Live.createLive(createLiveRequestDto, member, product);
        liveRepository.save(live);
        return live.getId();
    }

    public Live updateLive(String liveId, CreateLiveRequestDto createLiveRequestDto) {
        Live live = getLiveById(liveId);
        live.updateLive(createLiveRequestDto);
        return live;
    }
}
