package org.ssafy.pasila.domain.live.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateQsheetRequest {
    @Schema(description = "말투 스타일")
    private String style;

    @Schema(description = "판매자 이름")
    private String userName;

    @Schema(description = "제품명")
    private String productName;

    @Schema(description = "제품설명")
    private String description;
}
