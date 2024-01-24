package org.ssafy.pasila.domain.live.dto.request;

import lombok.Data;

@Data
public class CreateQsheetRequest {
    private String style;
    private String userName;
    private String productName;
    private String description;
}
