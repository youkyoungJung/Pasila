package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.LargeCategory;

public interface LargeCategoryRepository extends JpaRepository<LargeCategory, Long> {
}
