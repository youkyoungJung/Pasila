package org.ssafy.pasila.domain.live.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public enum LiveStatus {

    @Schema(description = "예약")
    RESERVED("예약"),

    @Schema(description = "진행")
    IN_PROGRESS("진행"),

    @Schema(description = "숏핑있음")
    IS_SHORTPING("숏핑있음"),

    @Schema(description = "숏핑없음")
    NOT_SHORTPING("숏핑없음");

    private final String description;

    LiveStatus(String description) {

        this.description = description;

    }
}
