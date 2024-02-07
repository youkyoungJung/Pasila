package org.ssafy.pasila.domain.order.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.List;
import java.util.Map;

public interface EmitterRepository {

    SseEmitter save(String id, SseEmitter emitter); //Emitter 저장

    void saveEventCache(String id, Object event); //이벤트 저장

    Map<String, SseEmitter> findAllEmitterStartWithByLiveId(String id); // 해당 라이브와 관련된 Emitter 찾기

    void deleteAllEmitterStartWithId(String id); //해당 라이브와 관련된 모든 Emitter 지우기

    List<SseEmitter> getAll(); //Emitter 모두 가져오기
}
