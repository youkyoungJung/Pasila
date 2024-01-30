package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Category {
    @Id
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(name = "image_url", length = 2083)
    private String imageUrl;
}
