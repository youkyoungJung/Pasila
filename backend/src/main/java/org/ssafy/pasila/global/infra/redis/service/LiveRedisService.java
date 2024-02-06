package org.ssafy.pasila.global.infra.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.global.infra.redis.dto.LiveRedisDto;
import org.ssafy.pasila.global.infra.redis.entity.LiveRedis;
import org.ssafy.pasila.global.infra.redis.repository.LiveRedisRepository;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LiveRedisService {

    private final LiveRedisRepository liveRedisRepository;

    private final LiveRepository liveRepository;

    //Redis에 라이브 추가
    public String addLive(String liveId, String title){

        LiveRedis liveRedis = new LiveRedis(liveId, title, 0);
        liveRedisRepository.save(liveRedis);
        return liveRedis.getId();

    }


    //Redis에 Like 수 업데이트
    @Transactional
    public int addLike(String liveId){

        LiveRedis liveRedis = liveRedisRepository.findById(liveId)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        liveRedis.addLike();
        liveRedisRepository.save(liveRedis);
        return liveRedis.getLikeCnt();

    }

    //Redis 에 id 별 LikeCnt 가져오기
    public int getLikeCnt(String liveId){

        LiveRedis liveRedis = liveRedisRepository.findById(liveId)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        return liveRedis.getLikeCnt();

    }

    //인기 Top3 Redis에서 가져오기
    public List<LiveRedisDto> getTop3LiveinRedis(){

        List<LiveRedisDto> result = new ArrayList<>();
        Iterable<LiveRedis> list = liveRedisRepository.findTop3ByOrderByLikeCntDesc();

        for (LiveRedis liveRedis : list) {
            LiveRedisDto dto = LiveRedisDto.builder()
                    .id(liveRedis.getId())
                    .title(liveRedis.getTitle())
                    .liveCnt(liveRedis.getLikeCnt())
                    .build();
            result.add(dto);
        }

        return result;

    }

    //인기 Top3 Redis에서 가져오기
    public List<SearchLiveResponseDto> getTop3Living(String keyword){

        List<SearchLiveResponseDto> result = new ArrayList<>();
        Iterable<LiveRedis> list = liveRedisRepository.findTop3ByOrderByLikeCntDesc();

        for (LiveRedis liveRedis : list) {
            Live live = liveRepository.findById(liveRedis.getId())
                    .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

            if(live.getTitle().contains(keyword)) {
                SearchLiveResponseDto dto = SearchLiveResponseDto.builder()
                        .liveId(liveRedis.getId())
                        .title(liveRedis.getTitle())
                        .sellerId(live.getMember().getId())
                        .name(live.getMember().getName())
                        .channel(live.getMember().getChannel())
                        .profile(live.getMember().getProfile())
                        .productId(live.getProduct().getId())
                        .productThumbnail(live.getProduct().getThumbnail())
                        .productName(live.getProduct().getName())
                        .price(live.getProduct().getProductOptions().get(0).getPrice())
                        .discountPrice(live.getProduct().getProductOptions().get(0).getDiscountPrice())
                        .build();
                result.add(dto);
            }
        }
        return result;

    }

}
