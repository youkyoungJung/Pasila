package org.ssafy.pasila.domain.sell.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.order.entity.Status;
import org.ssafy.pasila.domain.order.service.OrderService;
import org.ssafy.pasila.domain.sell.service.SellService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sell")
@Slf4j
public class SellController {

    private final SellService sellService;


    @Operation(summary = "ChangeStatus", description = "[판매자] 주문 상태 변경")
    @PutMapping("{id}/status")
    public ApiCommonResponse<?> changeStatus(@PathVariable Long id, @RequestBody String status) {

        Long orderId = sellService.changeStatus(id, status);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), orderId);

    }

    @Operation(summary = "GetStatusValues", description = "주문 상태 전체 보여주기")
    @GetMapping("/statusValues")
    public ApiCommonResponse<List<Map<String, String>>> getStatusValues() {

        List< Map<String, String>> list = new ArrayList<>();
        for (Status status : Status.values()) {
            Map<String, String> statusMap = new HashMap<>();
            statusMap.put("key", status.name());
            statusMap.put("desc", status.getDescription());
            list.add(statusMap);
        }
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), list);

    }

}
