package org.ssafy.pasila.domain.order.event;

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

    private String liveId;
    private List<ProductOptionDto> options;

}
