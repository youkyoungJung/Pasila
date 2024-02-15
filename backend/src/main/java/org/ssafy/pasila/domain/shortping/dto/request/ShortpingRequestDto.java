package org.ssafy.pasila.domain.shortping.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShortpingRequestDto {
    @Schema(description = "라이브 아이디")
    private String liveId;
    
    @Schema(description = "숏핑 제목")
    private String title;

    @Schema(description = "하이라이트 목록")
    private List<LivelogRequestDto> livelogs;

    public Shortping toEntity(String videoUrl, Product product) {
        return new Shortping(title, videoUrl, product);
    }
}
