package org.ssafy.pasila.domain.search.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.search.dto.SearchResponse;
import org.ssafy.pasila.domain.search.service.SearchService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Tag(name = "Search", description = "Search API")

public class SearchController {

    private final SearchService searchService;

    @GetMapping(value="/live")
    public List<SearchResponse> getAllResults(@Parameter String keyword){
        searchService.searchForLive(keyword);
        return null;
    }



}
