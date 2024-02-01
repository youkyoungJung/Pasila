package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDto {
    @Schema(description = "채널명")
    private String channel;
    @Schema(description = "채널 설명")
    private String description;
    @Schema(description = "프로필 사진")
    private String profile;
}
