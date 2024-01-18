package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetailCategory {

    @Id
    @Column(name = "detail_category_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "middle_category_id")
    MiddleCategory middleCategory;
    String name;

}
