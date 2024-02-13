package org.ssafy.pasila.domain.apihandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),

    /*
     * 400 BAD_REQUEST: 재고 부족
     * */
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "Not Enough Stock."),

    /*
     * 400 BAD_REQUEST: 재고 부족
     * */
    FAIL_SAVE_ORDER(HttpStatus.BAD_REQUEST, "Failed to save order after maxRetries retries"),

    /*
     * 400 BAD_REQUEST: 취소 불가
     * */
    NOT_CANCELLATION(HttpStatus.BAD_REQUEST, "Non-cancellable product."),

    /*
     * 401 UNAUTHORIZED: 인증되지 않은 사용자의 요청
     */
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "Unauthorized."),

    /*
     * 403 FORBIDDEN: 권한이 없는 사용자의 요청
     */
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "Forbidden."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    SESSION_NOT_EXIST(HttpStatus.NOT_FOUND, "Session does not exist."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    CHAT_NOT_EXIST(HttpStatus.NOT_FOUND, "Chat message does not exist"),

    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Not allowed method."),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server error."),

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),

    MAX_RETRIES_EXCEEDED(HttpStatus.NOT_FOUND, "Max retries exceeded."),

    /*
     * 502 - 504 OpenVidu Server is not available
     */

    OPENVIDU_SERVER_ERROR(HttpStatus.BAD_GATEWAY, "OpenVidu Server is not available.");

    private final HttpStatus httpStatus;

    private final String message;

}