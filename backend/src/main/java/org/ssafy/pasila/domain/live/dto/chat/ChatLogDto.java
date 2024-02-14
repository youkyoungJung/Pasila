package org.ssafy.pasila.domain.live.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogDto {

    @Schema(description = "라이브 방송 ID")
    private String liveId;

    @Schema(description = "채팅 내용")
    private String message;

}
