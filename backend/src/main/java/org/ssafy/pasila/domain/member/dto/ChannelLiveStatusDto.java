package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.live.entity.LiveStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelLiveStatusDto {

    /* LIVE */
    @Schema(description = "라이브 정보")
    private ChannelLiveDto live;

    private LiveStatus status;

}
