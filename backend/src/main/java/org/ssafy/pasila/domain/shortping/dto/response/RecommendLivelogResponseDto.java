package org.ssafy.pasila.domain.shortping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecommendLivelogResponseDto {

    @Schema(description = "하이라이트 시작 시간")
    private String start;

    @Schema(description = "하이라이트 종료 시간")
    private String end;

    @Schema(description = "하이라이트 제목")
    private String title;

}
