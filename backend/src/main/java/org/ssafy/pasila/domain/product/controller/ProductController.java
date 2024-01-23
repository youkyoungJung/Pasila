package org.ssafy.pasila.domain.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;
import org.ssafy.pasila.domain.product.repository.*;
import org.ssafy.pasila.domain.product.service.ProductService;

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
    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProduct(@RequestPart(value = "pr") ProductRequest productRequest,
                                                @RequestPart(value = "image", required = false) MultipartFile image) {

        try{
            productService.saveProduct(productRequest, image);
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
    // 추후 여러 Image 삭제시 수정 필요 (List<String> deleteImageFilesName)
    @PutMapping("/product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable("id") String id,
                                                 @RequestPart(value = "pr") ProductRequest request,
                                                 @RequestPart(value = "new_image", required = false) MultipartFile newImageName){

        try{
                log.info("request: {}", request);
                productService.updateProduct(id, request, newImageName);

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //상품 정보 삭제
    @DeleteMapping("/product/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") String id){

        try{
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("success: 삭제 완료");

        }catch(Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }






}
