package org.ssafy.pasila.domain.live.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public enum LiveStatus {

    @Schema(description = "예약")
    RESERVED("예약"),
    @Schema(description = "진행")
    IN_PROGRESS("진행"),
    @Schema(description = "종료")
    ENDED("종료");

    private final String description;

    LiveStatus(String description) {

        this.description = description;

    }
}
