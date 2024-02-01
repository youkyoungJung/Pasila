package org.ssafy.pasila.domain.apihandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiCommonResponse<T>{

    private static final String SUCCESS_STATUS = "success";

    private int statusCode;

    private String status;

    private T data;

    public static <T> ApiCommonResponse<T> successResponse(int statusCode, T data) {
        return new ApiCommonResponse<>(statusCode, SUCCESS_STATUS, data);
    }

    private ApiCommonResponse(int statusCode, String status, T data) {
        this.statusCode = statusCode;
        this.status = status;
        this.data = data;
    }

}