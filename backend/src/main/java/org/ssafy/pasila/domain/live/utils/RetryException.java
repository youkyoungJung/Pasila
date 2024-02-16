package org.ssafy.pasila.domain.live.utils;

import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;

public class RetryException extends RestApiException {
    public RetryException(ErrorCode errorCode) {
        super(errorCode);
    }

//    public RetryException(ErrorCode errorCode, Throwable cause) {
//        super(errorCode, cause);
//    }

}
