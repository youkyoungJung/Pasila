package org.ssafy.pasila.domain.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SuccessResponse {

    private final int statusCode;
    private final String status;
    private final String message;

}
