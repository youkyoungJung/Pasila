package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "detail_category")
public class DetailCategory {
    @Id
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(name = "middle_category_id")
    private Long middleCategoryId;
}
