package org.ssafy.pasila.domain.apihandler;

public class StockRaceConditionException extends RestApiException{

    public StockRaceConditionException(ErrorCode errorCode) {
        super(errorCode);
    }

}
