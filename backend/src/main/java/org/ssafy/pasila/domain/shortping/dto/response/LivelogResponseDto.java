package org.ssafy.pasila.domain.shortping.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class LivelogResponseDto {

    @Schema(description = "아이디")
    private Long id;

    @Schema(description = "시작 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime start;

    @Schema(description = "종료 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime end;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "자막")
    private String subtitle;

}
