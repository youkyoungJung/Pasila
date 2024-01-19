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
@NoArgsConstructor
public class MiddleCategory {

    @Id
    @Column(name = "middle_category_id")
    Long id;

    @OneToMany
    @JoinColumn(name = "large_category")
    List<LargeCategory> largeCategory;

    String name;

    public MiddleCategory(Long id){
        this.id = id;
    }


}
