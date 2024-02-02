package org.ssafy.pasila.domain.shortping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.time.LocalTime;

@Data
public class RecommendLivelogResponseDto {

    @Schema(description = "하이라이트 시작 시간")
    private String start;

    @Schema(description = "하이라이트 종료 시간")
    private String end;

    @Schema(description = "하이라이트 제목")
    private String title;

    public Livelog toEntity(Live live) {
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end);
        return new Livelog(startTime, endTime, title, null, live);
    }

}
