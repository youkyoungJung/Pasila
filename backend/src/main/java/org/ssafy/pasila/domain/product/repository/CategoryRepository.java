package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.entity.Category;
import org.ssafy.pasila.domain.product.entity.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
