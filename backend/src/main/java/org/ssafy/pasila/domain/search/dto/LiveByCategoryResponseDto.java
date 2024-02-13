package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LiveByCategoryResponseDto {
    @Schema(description = "상위 Top 5 Shortping")
    private List<SearchShortpingResponseDto> topShortpings;

    @Schema(description = "카테고리별 최신순 라이브 목록")
    private List<SearchLiveResponseDto> latestLives;

    @Schema(description = "카테고리별 인기순 라이브 목록")
    private List<SearchLiveResponseDto> popularLives;
}
