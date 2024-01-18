package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class DetailCategory {

    @Id
    @Column(name = "detail_category_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "middle_category_id")
    MiddleCategory middleCategory;
    String name;

}
