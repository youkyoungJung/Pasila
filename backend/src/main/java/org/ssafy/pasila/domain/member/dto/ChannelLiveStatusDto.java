package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelLiveStatusDto {
    /* LIVE */
    @Schema(description = "라이브 정보")
    private ChannelLiveDto live;

    /* STATUS */
    @Schema(description = "예약된 라이브")
    private boolean isReserve;

    @Schema(description = "진행중 라이브")
    private boolean isProgress;

    @Schema(description = "종료된 라이브")
    private boolean isEnd;
}
