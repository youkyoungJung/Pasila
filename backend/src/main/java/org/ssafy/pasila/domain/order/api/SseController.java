package org.ssafy.pasila.domain.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order/connect")
public class SseController {

    private final SseEmitters sseEmitters;

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //productId
    public ApiCommonResponse<?> connect() {
        SseEmitter emitter = new SseEmitter(); //생성자를 통해 만료시간 설정 가능
        sseEmitters.add(emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect") //해당 이벤트의 이름지정
                    .data("connected!") //503 에러 방지를 위한 더미 데이터
            );
        }catch (IOException e){
            throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
        }
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), emitter);

    }

    @PostMapping("/purchase")
    public ApiCommonResponse<?> count(){
        sseEmitters.count();
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "");
    }

}