package org.ssafy.pasila.domain.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.order.event.StockChangeEvent;
import org.ssafy.pasila.domain.order.repository.EmitterRepository;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseEmitterService {

    private static final Long DEFAULT_TIMEOUT = 10L * 1000 * 60; //10분
    private List<SseEmitter> deadEmitter = new CopyOnWriteArrayList<>();
    private final EmitterRepository emitterRepository;
    private final ProductOptionRepository productOptionRepository;
    private final LiveRepository liveRepository;

    /**SSE통신*/
    public SseEmitter subscribe(String liveId, String lastEventId){

        String emitterId = makeTimeIncludedId(liveId);
        SseEmitter emitter;

        //버그 방지용 코드
        if (emitterRepository.findAllEmitterStartWithByLiveId(liveId) != null){
            emitterRepository.deleteAllEmitterStartWithId(liveId);
            emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT)); //id가 key, SseEmitter가 value
        }
        else {
            emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT)); //id가 key, SseEmitter가 value
        }

        // SseEmitter 의 완료/시간초과/에러로 인한 전송 불가 시 sseEmitter 삭제
        emitter.onCompletion(() -> emitterRepository.deleteAllEmitterStartWithId(liveId));
        emitter.onTimeout(() -> emitterRepository.deleteAllEmitterStartWithId(liveId));
        emitter.onError((e) -> emitterRepository.deleteAllEmitterStartWithId(liveId));

        Live live = liveRepository.findById(liveId)
                .orElseThrow(()-> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));

        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(live.getProduct().getId())
                .stream()
                .map(option -> ProductOptionDto.builder()
                        .id(option.getId())
                        .name(option.getName())
                        .stock(option.getStock())
                        .price(option.getPrice())
                        .discountPrice(option.getDiscountPrice())
                        .productId(option.getProduct().getId())
                        .build()
                )
                .toList();

        // 연결 직후, 데이터 전송이 없을 시 503 에러 발생. 에러 방지 위한 더미데이터 전송
        StockChangeEvent notification = createNotification(liveId, options);
        sendToClient(emitter, liveId, notification);

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) { // 클라이언트가 미수신한 Event 유실 예방, 연결이 끊켰거나 미수신된 데이터를 다 찾아서 보내준다.
            Map<String, SseEmitter> events = emitterRepository.findAllEmitterStartWithByLiveId(liveId);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }


    @Transactional
    // 알림 보낼 로직에 send 메서드 호출하면 됨
    public void send(String receiver, List<ProductOptionDto> options) {

        StockChangeEvent notification = createNotification(receiver, options);

        // 로그인 한 유저의 SseEmitter 모두 가져오기
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByLiveId(receiver);

        sseEmitters.forEach(
                (key, emitter) -> {
                    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                    emitterRepository.saveEventCache(key, notification);
                    // 데이터 전송
                    sendToClient(emitter, key, notification);
                }
        );
    }

    private StockChangeEvent createNotification(String id, List<ProductOptionDto> options) {

        return StockChangeEvent.builder()
                .liveId(id)
                .options(options)
                .build();
    }

    public void sendToClient(SseEmitter emitter, String id, Object data) {

        try {
            emitter.onCompletion(() -> emitterRepository.deleteAllEmitterStartWithId(id));
            emitter.onTimeout(() -> emitterRepository.deleteAllEmitterStartWithId(id));
            emitter.onError((e) -> emitterRepository.deleteAllEmitterStartWithId(id));

            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            deadEmitter.add(emitter);
            emitterRepository.deleteAllEmitterStartWithId(id);
            log.error("SSE 연결 오류 발생", e);
        }

    }

    //Last-Event-ID의 값을 이용하여 유실된 데이터를 찾는데 필요한 시점을 파악하기 위한 형태
    private String makeTimeIncludedId(String liveId) { return liveId + "_" + System.currentTimeMillis(); }

    @Scheduled(fixedDelay = 10000) //10초마다 주기적으로 시행
    public void flushDeadEmitters() {
        deadEmitter.clear();
    }

}
