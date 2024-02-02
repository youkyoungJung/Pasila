package org.ssafy.pasila.domain.apihandler;

public class NotEnoughStockException extends RestApiException{

    public NotEnoughStockException(ErrorCode errorCode) {
        super(errorCode);
    }

}
