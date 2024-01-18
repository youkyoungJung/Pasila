package org.ssafy.pasila.domain.product.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.product.dto.ProductRequest;
import org.ssafy.pasila.domain.product.entity.DetailCategory;
import org.ssafy.pasila.domain.product.entity.LargeCategory;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.DetailCategoryRepository;
import org.ssafy.pasila.domain.product.repository.LargeCategoryRepository;
import org.ssafy.pasila.domain.product.repository.MiddleCategoryRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final LargeCategoryRepository largeCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final DetailCategoryRepository detailCategoryRepository;

    // Product 생성
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {

        log.info("largeCategoryId: {}", productRequest.getLargeCategoryId());
        Product product = productRequest.getProduct();
        Long largeCategoryId = productRequest.getLargeCategoryId();
        Long middleCategoryId = productRequest.getMiddleCategoryId();
        Long detailCategoryId = productRequest.getDetailCategoryId();

        // category 없을 경우 예외 발생
        LargeCategory largeCategory = largeCategoryRepository.findById(largeCategoryId)
                .orElseThrow(() -> new RuntimeException("LargeCategory not found"));
        MiddleCategory middleCategory = middleCategoryRepository.findById(middleCategoryId)
                .orElseThrow(() -> new RuntimeException("MiddleCategory not found"));
        DetailCategory detailCategory = detailCategoryRepository.findById(detailCategoryId)
                .orElseThrow(() -> new RuntimeException("DetailCategory not found"));

        // product에 categoryId 주입
        product.setLargeCategory(largeCategory);
        product.setMiddleCategory(middleCategory);
        product.setDetailCategory(detailCategory);

        // 생성 시간 저장
        product.setCreatedAt(LocalDateTime.now());

        // repository를 통한 저장
        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

}
