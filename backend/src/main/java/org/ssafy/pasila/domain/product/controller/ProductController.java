package org.ssafy.pasila.domain.product.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.product.dto.ProductRequest;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final LargeCategoryRepository largeCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final DetailCategoryRepository detailCategoryRepository;
    private final ProductJoinRepository productJoinRepository;

    // Product 생성
//    @PostMapping("/product")
//    public void createProduct(@RequestBody ProductRequest productRequest) {
//
////        Long largeCategoryId = productRequest.getLargeCategoryId();
////        Long middleCategoryId = productRequest.getMiddleCategoryId();
////        Long detailCategoryId = productRequest.getDetailCategoryId();
//
////        Product product = getProduct(largeCategoryId, middleCategoryId, detailCategoryId);
//
//        // repository를 통한 저장
//        productJoinRepository.save(productRequest);
//    }

    @GetMapping("/product")
    public List<ProductRequest> getAllProducts() {

        return productJoinRepository.findAllWithCategory();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id){
        return productRepository.findById(id);
    }

//    @PutMapping("/product/{id}")
//    public UpdateProductResponse updateProduct(@PathVariable("id") Long id, @Valid UpdateProductRequest request){
//
////       return new UpdateMemberResponse()
//    }

    @Data
    @AllArgsConstructor
    static class UpdateProductResponse{

            private Long id;
            private String name;
    }

    @Data
    static class UpdateProductRequest{

        private String name;
    }






}
