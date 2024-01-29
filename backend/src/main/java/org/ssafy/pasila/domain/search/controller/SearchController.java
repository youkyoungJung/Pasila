package org.ssafy.pasila.domain.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponse;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponse;
import org.ssafy.pasila.domain.search.service.SearchService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Tag(name = "Search", description = "Search API")

public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "Search Live", description = "Live에 대한 검색기능을 제공합니다.")
    //TODO: 0. 예외처리하기
    @GetMapping(value="/live")
    public List<SearchLiveResponse> getAllResultsForLive(@RequestParam String keyword, @RequestParam(name = "sort", defaultValue = "popularity") String sort){

        return searchService.searchForLive(keyword, sort);
    }

    @Operation(summary = "Search Shortping", description = "Shortping에 대한 검색기능을 제공합니다.")
    @GetMapping(value="/shortping")
    public List<SearchShortpingResponse> getAllResultsForShortping(@RequestParam String keyword, @RequestParam(name = "sort", defaultValue = "popularity") String sort){
        return searchService.searchForShortping(keyword, sort);
    }

}
