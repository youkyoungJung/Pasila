package org.ssafy.pasila.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import java.util.*;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    List<ProductOption> findAllByProduct_Id(String productId);

}