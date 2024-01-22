package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
