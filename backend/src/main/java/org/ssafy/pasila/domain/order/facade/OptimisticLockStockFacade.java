package org.ssafy.pasila.domain.order.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.StockRaceConditionException;
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.order.service.OrderService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

    private final OrderService orderService;

    /*Busy-waiting 상태를 피하는 Backoff 전략 */
    private int retries = 0;

    public List<Long> saveTrade(final OrderFormDto orderFormDto) throws InterruptedException {
        int maxRetries = 3; // 최대 재시도 횟수
        int initialBackoff = 10; // 밀리초 단위의 초기 재시도 간격

        int retries = 0;
        while (retries < maxRetries) {
            try {
                return orderService.saveOrder(orderFormDto);
            } catch (Exception e) {
                log.error("Error occurred while saving order. Retrying...", e);
                Thread.sleep(initialBackoff * (int) Math.pow(2, retries)); // Backoff 전략에 따라 재시도 간격 증가
                retries++; // 재시도 횟수 증가
            }
        }
        throw new IllegalStateException("Failed to save order after " + maxRetries + " retries");

    }
}
