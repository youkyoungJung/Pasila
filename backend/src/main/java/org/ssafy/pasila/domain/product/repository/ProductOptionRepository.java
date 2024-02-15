package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import java.util.*;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    List<ProductOption> findAllByProduct_Id(String productId);

}