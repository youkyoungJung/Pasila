package org.ssafy.pasila.domain.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.domain.search.dto.SearchResponse;
import org.ssafy.pasila.domain.search.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    /** product name, live_title, channel_name
     *  검색 키워드에 해당하는 Live 모음집
     * */
    public List<SearchResponse> searchForLive(String keyword){
        return searchRepository.findAllByName(keyword);
    }

    /**product name, live_title, short_title
     *  검색 키워드에 해당하는 Shortping 모음집
     * */
    public List<SearchResponse> searchForShortping(){
        List<SearchResponse> result = new ArrayList<>();


        return result;
    }


}
