package org.ssafy.pasila.domain.shortping.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LivelogRequest {

    @Schema(description = "하이라이트 제목")
    private String title;

    @Schema(description = "시작 시간")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime start;

    @Schema(description = "종료 시간")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime end;

    @Schema(description = "자막")
    private String subtitle;

    public Livelog toEntity(Live live) {
        Livelog livelog = Livelog.builder()
                .title(title)
                .start(start)
                .end(end)
                .subtitle(subtitle)
                .build();
        livelog.setLive(live);
        return livelog;
    }
}
