package org.ssafy.pasila.domain.apihandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiCommonResponse<T>{

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private HttpStatus statusCode;
    private String status;
    private T data;
    private String message;

    public static <T> ApiCommonResponse<T> successResponse(HttpStatus statusCode, T data) {
        return new ApiCommonResponse<>(statusCode, SUCCESS_STATUS, data, null);
    }

    public static ApiCommonResponse<?> successWithNoContent(HttpStatus statusCode) {
        return new ApiCommonResponse<>(statusCode, SUCCESS_STATUS, null, null);
    }

    private ApiCommonResponse(HttpStatus statusCode, String status, T data, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.data = data;
        this.message = message;
    }

}
