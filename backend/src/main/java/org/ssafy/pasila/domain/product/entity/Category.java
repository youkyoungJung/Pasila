package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
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