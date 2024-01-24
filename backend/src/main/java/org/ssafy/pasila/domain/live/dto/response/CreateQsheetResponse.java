package org.ssafy.pasila.domain.live.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateQsheetResponse {
    @Schema(description = "큐시트")
    private String qsheet;
    public CreateQsheetResponse(String qsheet) {
        this.qsheet = qsheet;
    }
}
