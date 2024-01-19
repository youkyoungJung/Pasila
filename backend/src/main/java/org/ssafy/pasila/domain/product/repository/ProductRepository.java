package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
