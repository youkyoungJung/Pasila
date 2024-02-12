package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShortpingByCategoryResponseDto {
    @Schema(description = "Top5 숏핑 리스트")
    private List<SearchShortpingResponseDto> top5Shortping;

    @Schema(description = "최신순 숏핑 리스트")
    private List<SearchShortpingResponseDto> latestShortping;

    @Schema(description = "인기순 숏핑 리스트")
    private List<SearchShortpingResponseDto> popularShortping;
}
