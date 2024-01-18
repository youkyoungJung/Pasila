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
public class MiddleCategory {

    @Id
    @Column(name = "middle_category_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "large_category_id")
    LargeCategory largeCategory;

    String name;

}
