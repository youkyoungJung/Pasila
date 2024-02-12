package org.ssafy.pasila.domain.live.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class homeLiveResponseDto {
    @Schema(description = "상위 Top 5 Shortping")
    private List<ChannelShortpingDto> topShortpings;

    @Schema(description = "카테고리별 라이브 목록")
    private List<ChannelLiveDto> lives;
}
