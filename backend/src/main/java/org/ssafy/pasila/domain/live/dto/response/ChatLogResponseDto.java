package org.ssafy.pasila.domain.live.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ChatLogResponseDto {

    @Schema(description = "라이브 방송 ID")
    private String liveId;

    @Schema(description = "채팅 내용")
    private String message;

    @Schema(description = "사용자 이름")
    private  String name;

    @Schema(description = "사용자 프로필 사진")
    private String profile;

    public ChatLogResponseDto(String liveId, String message, String name, String profile){
        this.liveId = liveId;
        this.message = message;
        this.name = name;
        this.profile = profile;
    }

}