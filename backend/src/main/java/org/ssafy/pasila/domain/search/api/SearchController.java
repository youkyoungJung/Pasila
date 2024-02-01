package org.ssafy.pasila.domain.search.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;
import org.ssafy.pasila.domain.search.service.SearchService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Tag(name = "Search", description = "Search API")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "Search Live", description = "Live에 대한 검색기능을 제공합니다.")
    @GetMapping(value="/live")
    public ApiCommonResponse<?> getAllResultsForLive(@RequestParam String keyword, @RequestParam(name = "sort", defaultValue = "popularity") String sort){

        List<SearchLiveResponseDto> result = searchService.searchForLive(keyword, sort);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);

    }

    @Operation(summary = "Search Shortping", description = "Shortping에 대한 검색기능을 제공합니다.")
    @GetMapping(value="/shortping")
    public ApiCommonResponse<?> getAllResultsForShortping(@RequestParam String keyword, @RequestParam(name = "sort", defaultValue = "popularity") String sort){

        List<SearchShortpingResponseDto> result = searchService.searchForShortping(keyword, sort);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);

    }

}