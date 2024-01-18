package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductRepository;

/**
 * product service
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //상품 등록
    @Transactional
    public Long saveProduct(Product product){
        productRepository.save(product);
        return product.getId();
    }

    //상품 수정
//    @Transactional
//    public void updateProduct(Long id, String name, int price, int stockQuantity) {
//        Product product = productRepository.findOne(id);
//        product.setName(name);
//    }

    //상품 삭제

}
