package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.NotEnoughStockException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "product_option")
@ToString(exclude = "product")
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

    //== 연관메서드==//
    public void addProduct(Product product){
        this.product = product;
    }

    public void updateProductOption(ProductOption productOption){
        this.id = productOption.getId();
        this.name = productOption.getName();
        this.stock = productOption.getStock();
        this.price = productOption.getPrice();
        this.discountPrice = productOption.getDiscountPrice();
    }

    //== 재고 관련 메서드 ==//
    public void removeStock(int quantity){
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException(ErrorCode.NOT_ENOUGH_STOCK);
        }
        this.stock = restStock;
    }

    public void addStock(int quantity){
        this.stock += quantity;
    }
}
