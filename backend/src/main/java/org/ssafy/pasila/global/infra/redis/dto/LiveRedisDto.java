package org.ssafy.pasila.global.infra.redis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LiveRedisDto {

    @Schema(description = "라이브 id")
    private String id;

    @Schema(description = "라이브 제목")
    private String title;

    @Schema(description = "라이브 좋아요 수")
    private int liveCnt;

    @Builder
    public LiveRedisDto(String id, String title, int liveCnt){
        this.id = id;
        this.title = title;
        this.liveCnt = liveCnt;
    }

}
