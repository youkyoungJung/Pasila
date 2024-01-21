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
@Table(name = "middle_category")
public class MiddleCategory {
    @Id
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(name = "large_category_id")
    private Long largeCategoryId;
}
