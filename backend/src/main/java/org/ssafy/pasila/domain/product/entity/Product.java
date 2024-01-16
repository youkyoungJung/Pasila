package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Product Entity 파일
 * JPA 테이블 생성
 */
@Entity
@Lazy
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;

    @Column(nullable = false)
    @JsonProperty
    private String sellerId;
    private int largeCategoryId;
    private int middleCategoryId;
    private int detailCategoryId;

    @Column(nullable = false)
    @JsonProperty
    private String name;
    private String desc;
    private Date created_at;

}