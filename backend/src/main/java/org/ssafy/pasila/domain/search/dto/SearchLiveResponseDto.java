package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchLiveResponseDto {

    @Schema(description = "라이브 id")
    private String liveId;

    @Schema(description = "라이브 제목")
    private String title;

    @Schema(description = "판매자 이름")
    private String name;

    @Schema(description = "판매자 채널명")
    private String channel;

    @Schema(description = "제품 이름")
    private String productName;

}