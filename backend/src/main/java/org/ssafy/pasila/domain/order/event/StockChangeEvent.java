package org.ssafy.pasila.domain.order.event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockChangeEvent {

    @Schema(description = "라이브 id")
    private String liveId;

    @Schema(description = "옵션 정보")
    private List<ProductOptionDto> options;

}