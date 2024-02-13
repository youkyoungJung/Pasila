package org.ssafy.pasila.domain.shortping.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.time.LocalTime;

@Data
@Builder
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

    public static LivelogResponseDto of(Livelog livelog) {
        return LivelogResponseDto.builder()
                .id(livelog.getId())
                .start(livelog.getStart())
                .end(livelog.getEnd())
                .title(livelog.getTitle())
                .build();
    }

}
