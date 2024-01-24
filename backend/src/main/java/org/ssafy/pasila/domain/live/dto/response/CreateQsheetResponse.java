package org.ssafy.pasila.domain.live.dto.response;

import lombok.Data;

@Data
public class CreateQsheetResponse {
    private String qsheet;
    public CreateQsheetResponse(String qsheet) {
        this.qsheet = qsheet;
    }
}
