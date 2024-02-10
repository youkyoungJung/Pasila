package org.ssafy.pasila.domain.live.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LiveStatsResponseDto {
    @Schema(description = "좋아요 수")
    private Integer likeCnt;

    @Schema(description = "라이브 시작 시간")
    private LocalDateTime liveOnAt;

    @Schema(description = "라이브 종료 시간")
    private LocalDateTime liveOffAt;

    @Schema(description = "총 방송 시간")
    private String totalBroadcastTime;

    @Schema(description = "시청자 수")
    private int participant;

}
