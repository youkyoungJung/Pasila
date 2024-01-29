package org.ssafy.pasila.domain.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public ResponseEntity<Map<String, Object>> handleSuccess(int status, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", status);
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> exceptionHandler(int status, Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", status);
        result.put("error", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
