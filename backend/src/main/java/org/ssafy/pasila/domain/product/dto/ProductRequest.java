package org.ssafy.pasila.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.product.entity.DetailCategory;
import org.ssafy.pasila.domain.product.entity.LargeCategory;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;
import org.ssafy.pasila.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
//    private Product product;

    private Long id;
//    private Long sellerId;
//    private String name;
//    private String description;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private String thumbnail;

    private Long largeCategoryId;
    private Long middleCategoryId;
//    private Long detailCategoryId;

}
