package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Label;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Product Entity 파일
 * JPA 테이블 생성
 */
@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String seller_id;

//    @ManyToOne
//    private LargeCategory largeCategory_id;
//    @ManyToOne
//    private MiddleCategory middleCategory_id;
//    @ManyToOne
//    private DetailCategory detailCategory_id;

    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    private String thumbnail;

}