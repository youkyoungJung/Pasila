package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MiddleCategory {

    @Id
    @Column(name = "middle_category_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "large_category_id")
    LargeCategory largeCategory;

    String name;
}
