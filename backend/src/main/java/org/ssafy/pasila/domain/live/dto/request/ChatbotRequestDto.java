package org.ssafy.pasila.domain.live.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatbotRequestDto {
    @Schema(description = "라이브 아이디")
    private String liveId;
    
    @Schema(description = "메세지")
    private String message;
}
