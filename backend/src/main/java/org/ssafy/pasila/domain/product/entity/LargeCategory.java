package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "large_category")
@NoArgsConstructor
public class LargeCategory {

    @Id
    @Column(name = "large_category_id")
    Long id;
    String name;

    @Column(name = "image_url")
    String imageUrl;

    @OneToMany(mappedBy = "largeCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public void add(Product product){
        product.setLargeCategory(this);
        getProducts().add(product);
    }

    public LargeCategory(Long id){
        this.id = id;
    }

}
