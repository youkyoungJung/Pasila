package org.ssafy.pasila.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class TokenDto {

    @Schema(description = "토큰 정보")
    private String accessToken;

}
