package org.ssafy.pasila.domain.error;

public class SuccessException extends RuntimeException {
    public SuccessException(String message) {
        super(message);
    }
}
