package org.ssafy.pasila.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String sellerId;
    private int largeCategoryId;
    private int middleCategoryId;
    private int detailCategoryId;
    private String name;
    private String desc;
    private Date created_at;
}
