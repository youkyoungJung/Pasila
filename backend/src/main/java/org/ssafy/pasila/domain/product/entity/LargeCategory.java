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
@Table(name = "large_category")
public class LargeCategory {
    @Id
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(name = "image_url", length = 2083)
    private String imageUrl;
}
