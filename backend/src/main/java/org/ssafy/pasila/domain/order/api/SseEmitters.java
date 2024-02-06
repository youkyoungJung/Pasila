package org.ssafy.pasila.domain.order.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class SseEmitters {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    //thread-safe 한 자료구조 : ConcurrnetModificationException 위험 요소제거

    private static final AtomicInteger counter = new AtomicInteger();

    public SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

    public void count() {
        int count = counter.incrementAndGet();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("count")
                        .data(count));
            } catch (IOException e) {
                throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
            }
        });
    }
}
