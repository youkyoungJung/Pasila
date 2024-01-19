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
import org.ssafy.pasila.domain.product.service.ProductService;

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
    private final ProductService productService;

    // Product 생성, 추후 @Valid 설정
    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {

        try{
            productService.saveProduct(productRequest);
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

    // id 에 따른 상품 조회,
    // 추후 카테고리 정보 가져오기 (완료)
    @GetMapping("/product/{id}")
    public Optional<ProductResponse> getProduct(@PathVariable("id") Long id){
        return Optional.ofNullable(productJoinRepository.findById(id));
    }

    // 상품 정보 수정
    // 추후 request에 @Valid 설정
    @PutMapping("/product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request){

        try{
            log.info("request: {}", request);
            productService.updateProduct(id, request);
            return ResponseEntity.status(HttpStatus.OK).body("success");

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    //상품 정보 삭제
    @DeleteMapping("/product/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){

        try{
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("success: 삭제 완료");

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }





}
