package org.ssafy.pasila.domain.order.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.order.service.OrderService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Save order", description = "주문을 등록한다.")
    @PostMapping
    public ApiCommonResponse<?> createProduct(@RequestBody OrderFormDto orderFormDto) throws IOException {

        Long orderId = orderService.saveOrder(orderFormDto);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), orderId);

    }


}
