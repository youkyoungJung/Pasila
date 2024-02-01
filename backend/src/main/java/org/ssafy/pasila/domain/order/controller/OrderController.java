package org.ssafy.pasila.domain.order.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.order.dto.OrderDto;
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.order.service.OrderService;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Save order", description = "주문을 등록한다.")
    @PostMapping
    public ApiCommonResponse<?> createOrder(@RequestBody OrderFormDto orderFormDto) throws IOException {

        Long orderId = orderService.saveOrder(orderFormDto);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), orderId);

    }

    @Operation(summary = "Get Order List", description = "[구매자]주문 내역을 조회")
    @GetMapping("/{id}")
    public ApiCommonResponse<?> getOrderList(@PathVariable Long id) throws IOException {

        List<OrderDto> orderList = orderService.getOrderList(id);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), orderList);

    }


}
