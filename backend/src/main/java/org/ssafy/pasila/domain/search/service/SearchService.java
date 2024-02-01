package org.ssafy.pasila.domain.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;
import org.ssafy.pasila.domain.search.repository.SearchRepository;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    /** product name, live_title, channel_name
     *  검색 키워드에 해당하는 Live 모음집
     * */
    public List<SearchLiveResponseDto> searchForLive(String keyword, String sort){

        return searchRepository.findAllByNameForLive(keyword, sort);

    }

    /**product name, live_title, short_title
     *  검색 키워드에 해당하는 Shortping 모음집
     * */
    public List<SearchShortpingResponseDto> searchForShortping(String keyword, String sort){

        return searchRepository.findAllByNameForShortping(keyword, sort);

    }

}