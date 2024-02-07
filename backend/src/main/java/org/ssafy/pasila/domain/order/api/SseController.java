package org.ssafy.pasila.domain.order.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.order.event.StockChangeEvent;
import org.ssafy.pasila.domain.order.service.SseEmitterService;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/real-time")
public class SseController {

    private final List<SseEmitter> emitters = new ArrayList<>();
    private final SseEmitterService sseEmitterService;

    /*
    response.setHeader("X-Accel-Buffering", "no"); // NGINX PROXY 에서의 필요설정
    response.setCharacterEncoding("UTF-8");
     */

    @GetMapping("/subscribe/{liveId}")
    public SseEmitter getStockUpdates(@PathVariable String liveId, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
        return sseEmitterService.subscribe(liveId, lastEventId);
    }

    public void sendStockUpdates(StockChangeEvent event){

        for(SseEmitter emitter : new ArrayList<>(emitters)) {
            try {
                emitter.send(SseEmitter.event().data(event));
            } catch (IOException e) {
                emitters.remove(emitter);
                throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
            }
        }
    }
}
