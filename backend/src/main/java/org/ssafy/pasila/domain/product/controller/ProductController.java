package org.ssafy.pasila.domain.product.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.product.entity.DetailCategory;
import org.ssafy.pasila.domain.product.entity.LargeCategory;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductJoinRepository productJoinRepository;

    // Product 생성, 추후 @Valid 설정
    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {

        LargeCategory largeCategory = new LargeCategory(productRequest.getLargeCategoryId());
        MiddleCategory middleCategory = new MiddleCategory(productRequest.getMiddleCategoryId());
        DetailCategory detailCategory = new DetailCategory(productRequest.getDetailCategoryId());

        try{
            log.info("largeCategoryId: {}", productRequest.getLargeCategoryId());
            Product savedProduct = productRequest.getProduct();
            savedProduct.setLargeCategory(largeCategory);
            savedProduct.setMiddleCategory(middleCategory);
            savedProduct.setDetailCategory(detailCategory);
            savedProduct.getDetailCategory().setId(productRequest.getDetailCategoryId());
            savedProduct.setCreatedAt(LocalDateTime.now());

            // repository를 통한 저장
            productRepository.save(savedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    // 모든 상품 조회
    @GetMapping("/product")
    public List<ProductResponse> getAllProducts() {
        return productJoinRepository.findAllWithCategory();
    }

    //id 에 따른 상품 조회,
    // 추후 카테고리 정보 가져오기
    @GetMapping("/product/{id}")
    public Optional<ProductResponse> getProduct(@PathVariable("id") Long id){
        return Optional.ofNullable(productJoinRepository.findById(id));
    }

    // 추후 request에 @Valid 설정
    @PutMapping("/product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable("id") Long id, ProductRequest request){

        try{

            return ResponseEntity.status(HttpStatus.OK).body("success");

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }






}
