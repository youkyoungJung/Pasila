package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "product_option")
public class ProductOption {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    private Integer stock;

    private Integer price;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public void updateProductOption(ProductOption productOption){
        this.id = productOption.getId();
        this.name = productOption.getName();
        this.stock = productOption.getStock();
        this.price = productOption.getPrice();
        this.discountPrice = productOption.getDiscountPrice();
    }

    //== 생성 메서드 ==//
    public void addProduct(Product product){
        this.product = product;
    }

}
