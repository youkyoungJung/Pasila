package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchShortpingResponseDto {

    @Schema(description = "숏핑 id")
    private String id;

    @Schema(description = "숏핑 제목")
    private String title;

}