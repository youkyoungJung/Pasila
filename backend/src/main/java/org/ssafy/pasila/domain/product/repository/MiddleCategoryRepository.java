package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {
}
