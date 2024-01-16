package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.Product;

/**
 * product repository
 * jpa 결과 반환(DAO)
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
