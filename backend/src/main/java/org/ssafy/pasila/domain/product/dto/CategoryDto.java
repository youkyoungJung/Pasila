package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Schema(description = "카테고리 id")
    private Long id;

    @Schema(description = "카테고리 제목")
    private String name;

    @Schema(description = "카테고리 이미지 url")
    private String imageUrl;

}
