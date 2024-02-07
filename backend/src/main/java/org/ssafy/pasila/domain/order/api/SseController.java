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
import org.ssafy.pasila.domain.order.dto.OrderDto;
import org.ssafy.pasila.domain.order.event.InventoryUpdateEvent;
import org.ssafy.pasila.domain.order.service.InventoryService;
import org.ssafy.pasila.domain.order.service.OrderService;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order/connect")
public class SseController {

    private final SseEmitters sseEmitters;

    private final OrderService orderService;

    private final InventoryService inventoryService;

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

    // 재고 업데이트 후 SSE를 통해 클라이언트로 전송
//    public void updateInventoryAndSendEvent(OrderDto order) {
//        int remainingQuantity = inventoryService.decreaseInventory(order);
//
//        // 새로운 재고 정보를 생성하여 클라이언트로 전송
//        InventoryUpdateEvent inventoryUpdateEvent = new InventoryUpdateEvent(order.getProduct().getName(), remainingQuantity);
//
//        // 모든 연결된 클라이언트에게 이벤트 전송
//        for (SseEmitter emitter : sseEmitters) {
//            try {
//                emitter.send(inventoryUpdateEvent);
//            } catch (IOException e) {
//                // 클라이언트 연결이 종료되었을 때 발생하는 예외 처리
//                sseEmitters.remove(emitter);
//            }
//        }
//    }

//    @PostMapping("/purchase")
//    public ApiCommonResponse<?> count(){
//        sseEmitters.count();
//        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "");
//    }

}