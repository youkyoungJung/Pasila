package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.dto.request.CreateLiveRequestDto;
import org.ssafy.pasila.domain.live.dto.response.LiveStatsResponseDto;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveQueryRepository;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.redis.service.ChatRedisService;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LiveService {

    private final RedisTemplate<String, String> redisTemplate;

    private final LiveRepository liveRepository;

    private final LiveQueryRepository liveQueryRepository;

    private final ChatRedisService chatRedisService;

    private final GptClient gptClient;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    @Transactional
    public int joinLive(String liveId , String name) {

        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("participant : " + liveId, name);
        return setOperations.size("participant : " + liveId).intValue();

    }

    @Transactional
    public int exitLive(String liveId ,String name) {

        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.remove("participant : " + liveId, name);
        return setOperations.size("participant : " + liveId).intValue();

    }

    public int deleteParticipantInRedis(String liveId) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        int participantCnt = 0;
        if(Boolean.TRUE.equals(redisTemplate.hasKey("participant : " + liveId))){
            participantCnt = setOperations.size("participant : " + liveId).intValue();
            redisTemplate.delete("participant : " + liveId);
        }
        return participantCnt;
    }

    @Transactional
    public void updateLiveOn(String liveId) {
        Live live = getLiveById(liveId);
        live.setLiveOn();
    }

    @Transactional
    public void updateLiveOff(String liveId, String fullVideoUrl, int likeCnt) {
        Live live = getLiveById(liveId);
        live.setLiveOff(fullVideoUrl, likeCnt);
    }

    public Live getLiveById(String id) {
        return liveRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public String getProductId(String liveId) {
        Live live = liveRepository.findById(liveId).orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        return live.getProduct().getId();
    }

    public LiveStatsResponseDto calcLiveStats(String liveId, int participantCnt) {
        Live live = liveRepository.findById(liveId).orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        return live.liveStats(participantCnt);
    }

    public List<String> getTop5Question(String liveId) {

        String chatList = chatRedisService.getChatList(liveId);

        if(chatList == null) {
            throw new RestApiException(ErrorCode.CHAT_NOT_EXIST);
        }

        String result = gptClient.questionSummary(chatList).replaceAll("- ", "");

        return List.of(result.split("\n"));
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

    @Transactional
    public String deleteLive(String id) {
        Live live = getLiveById(id);
        live.setActive(false);
        return live.getId();
    }

//    public List<ChannelLiveDto> getScheduledLiveByDate(LocalDate date) {
//
//        return liveQueryRepository.findScheduledLiveByDate(date);
//    }
    public List<ChannelLiveDto> getScheduledLive(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        if(startOfDay.isBefore(LocalDateTime.now())){
            startOfDay = LocalDateTime.now();
        }

        List<Live> scheduledLives = liveRepository.findByIsActiveTrueAndLiveScheduledAtGreaterThanEqualAndLiveScheduledAtLessThanOrderByLiveScheduledAtAsc(startOfDay, endOfDay);

        return scheduledLives.stream()
                .map(ChannelLiveDto::new)
                .collect(Collectors.toList());
    }
}
