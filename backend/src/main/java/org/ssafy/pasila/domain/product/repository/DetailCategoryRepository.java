package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.DetailCategory;

public interface DetailCategoryRepository extends JpaRepository<DetailCategory, Long> {
}
