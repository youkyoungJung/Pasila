package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;
import org.ssafy.pasila.domain.product.entity.DetailCategory;
import org.ssafy.pasila.domain.product.entity.LargeCategory;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductJoinRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * product service
 */
@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductJoinRepository productJoinRepository;

    //상품 등록
    @Transactional
    public void saveProduct(ProductRequest productRequest){

        LargeCategory largeCategory = new LargeCategory(productRequest.getLargeCategoryId());
        MiddleCategory middleCategory = new MiddleCategory(productRequest.getMiddleCategoryId());
        DetailCategory detailCategory = new DetailCategory(productRequest.getDetailCategoryId());


        log.info("largeCategoryId: {}", productRequest.getLargeCategoryId());
        Product savedProduct = productRequest.getProduct();
        savedProduct.setLargeCategory(largeCategory);
        savedProduct.setMiddleCategory(middleCategory);
        savedProduct.setDetailCategory(detailCategory);
        savedProduct.getDetailCategory().setId(productRequest.getDetailCategoryId());
        savedProduct.setCreatedAt(LocalDateTime.now());

        // repository를 통한 저장
        productRepository.save(savedProduct);

    }

    //상품 수정
    @Transactional
    public void updateProduct(Long id, ProductRequest productRequest) {

        LargeCategory largeCategory = new LargeCategory(productRequest.getLargeCategoryId());
        MiddleCategory middleCategory = new MiddleCategory(productRequest.getMiddleCategoryId());
        DetailCategory detailCategory = new DetailCategory(productRequest.getDetailCategoryId());

        Product result = productJoinRepository.findOne(id);
        result.setName(productRequest.getProduct().getName());
        result.setDescription(productRequest.getProduct().getDescription());
        result.setThumbnail(productRequest.getProduct().getThumbnail());
        result.setUpdatedAt(LocalDateTime.now());
        result.setLargeCategory(largeCategory);
        result.setMiddleCategory(middleCategory);
        result.setDetailCategory(detailCategory);
        log.info("반영 result : {}", result);
    }

    //상품 삭제

}
