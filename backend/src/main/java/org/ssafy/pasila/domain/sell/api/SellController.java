package org.ssafy.pasila.domain.sell.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.sell.dto.OrderManagementDetailDto;
import org.ssafy.pasila.domain.sell.dto.OrderManagementDto;
import org.ssafy.pasila.domain.sell.service.SellService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sell")
@Slf4j
@Tag(name = "Sell", description = "Sell API")
public class SellController {

    private final SellService sellService;

    @Operation(summary = "GetSellProdcut", description = "[판매자] 주문 내역 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderManagementDto.class)))
                    })})
    @GetMapping
    public ApiCommonResponse<?> getSellProductList(@RequestParam Long id){ //판매자 id

        List<OrderManagementDto> list = sellService.getSellProductList(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), list);

    }

    /**
     * 판매자 주문 내역 상세 조회
     * */
    @Operation(summary = "GetSellDetailList", description = "[판매자] 주문 내역 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderManagementDetailDto.class)))
                    })})
    @GetMapping("/{id}/detail")
    public ApiCommonResponse<?> getSellProductDetail(@PathVariable String id){ //제품 id

        List<OrderManagementDetailDto> list = sellService.getSellProductDetail(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), list);

    }

}