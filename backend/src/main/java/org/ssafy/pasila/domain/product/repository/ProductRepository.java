package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.pasila.domain.product.dto.ProductRequest;
import org.ssafy.pasila.domain.product.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.largeCategory " +
            "LEFT JOIN FETCH p.middleCategory " +
            "LEFT JOIN FETCH p.detailCategory")
    List<Product> findAllWithCategories();
}
