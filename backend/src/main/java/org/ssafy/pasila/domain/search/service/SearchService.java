package org.ssafy.pasila.domain.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;
import org.ssafy.pasila.domain.search.dto.ShortpingByCategoryResponseDto;
import org.ssafy.pasila.domain.search.repository.SearchRepository;
import org.ssafy.pasila.global.infra.redis.dto.LiveRedisDto;
import org.ssafy.pasila.global.infra.redis.repository.LiveRedisRepository;

import org.ssafy.pasila.global.infra.redis.service.LiveRedisService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    private final LiveRedisService liveRedisService;

    /** product name, live_title, channel_name
     *  검색 키워드에 해당하는 Live 모음집 (Redis : 방송중)
     * */
    public Page<SearchLiveResponseDto> searchForLive(String keyword, String sort, Pageable pageable){

        List<SearchLiveResponseDto> result = new ArrayList<>();

        List<SearchLiveResponseDto> redisLive = liveRedisService.getTop3Living(keyword);

        // 이전 페이지의 마지막 아이템보다 작은 ID를 가진 DB 데이터를 가져옴
        Page<SearchLiveResponseDto> dbLike = searchRepository.findAllForLive(keyword, sort, pageable);
        result.addAll(redisLive);
        result.addAll(dbLike.getContent());

        return new PageImpl<>(result, pageable, dbLike.getTotalPages());

    }


    /**product name, live_title, short_title
     *  검색 키워드에 해당하는 Shortping 모음집
     * */
    public List<SearchShortpingResponseDto> searchForShortping(String keyword, String sort){

        return searchRepository.findAllShortpingByFilter(0L, keyword, sort);

    }

    public ShortpingByCategoryResponseDto searchShortpingByCategory(Long categoryId) {

        List<SearchShortpingResponseDto> top5 = searchRepository.top5Shortping(categoryId);
        List<SearchShortpingResponseDto> latest = searchRepository.findAllShortpingByFilter(categoryId, "", "latest");
        List<SearchShortpingResponseDto> popular = searchRepository.findAllShortpingByFilter(categoryId, "", "popularity");

        return new ShortpingByCategoryResponseDto(top5, latest, popular);
    }

}