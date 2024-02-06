package org.ssafy.pasila.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MsgDto {

    @Schema(description = "핸드폰 번호")
    private String phoneNum;

    @Schema(description = "인증 번호")
    private String authNum;

}
