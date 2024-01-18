package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class LargeCategory {

    @Id
    @Column(name = "large_category_id")
    Long id;
    String name;

    @Column(name = "image_url")
    String imageUrl;

    @OneToMany(mappedBy = "largeCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
