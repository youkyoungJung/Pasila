package org.ssafy.pasila.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelResponse {
    @Schema(description = "채널명")
    private String channel;
    @Schema(description = "채널 설명")
    private String description;
    @Schema(description = "프로필 사진")
    private String profile;
}
