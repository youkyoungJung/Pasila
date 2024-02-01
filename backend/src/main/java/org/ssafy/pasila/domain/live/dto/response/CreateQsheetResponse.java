package org.ssafy.pasila.domain.live.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateQsheetResponse {

    @Schema(description = "큐시트")
    private String qsheet;

}
