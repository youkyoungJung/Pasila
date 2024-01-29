package org.ssafy.pasila.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.*;
import org.ssafy.pasila.domain.product.service.ProductService;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Product", description = "Product API")

public class ProductController {

    private final ProductJoinRepository productJoinRepository;
    private final ProductService productService;

    // Product 생성, 추후 @Valid 설정
    @Operation(summary = "Save product", description = "상품을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class)))
            })})
    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiCommonResponse<Product> createProduct(@RequestPart(value = "pr") ProductRequest productRequest,
                                                    @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        Product product = productService.saveProduct(productRequest, image);

//        log.info("controller: {}", product);
        return ApiCommonResponse.successResponse(HttpStatus.CREATED, product);

    }

    // 모든 상품 조회 (카테고리 조인)
    @Operation(summary = "get all product with category", description = "모든 상품을 조회한다.(카테고리까지 나옴)")
    @GetMapping("/product")
    public ApiCommonResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> list = productJoinRepository.findAllWithCategory();
        return ApiCommonResponse.successResponse(HttpStatus.ACCEPTED, list);
//        return productJoinRepository.findAllWithCategory();
    }

    // id 에 따른 상품 조회 (카테고리 조인)
    @Operation(summary = "get product", description = "상품을 조회한다(id)")
    @GetMapping("/product/{id}")
    public ApiCommonResponse<ProductResponse> getProduct(@PathVariable("id") String id){
        ProductResponse response = productJoinRepository.findById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK, response);

    }

    // 상품 정보 수정
    @Operation(summary = "update product", description = "상품을 수정한다.")
    @PutMapping("/product/{id}")
    public  ApiCommonResponse<Product> updateProduct(@PathVariable("id") String id,
                                                 @RequestPart(value = "pr") ProductRequest request,
                                                 @RequestPart(value = "new_image", required = false) MultipartFile newImageName) throws IOException {

        log.info("request: {}", request);
        Product product = productService.updateProduct(id, request, newImageName);
        return ApiCommonResponse.successResponse(HttpStatus.CREATED, product);

    }
    //상품 정보 삭제 - isActive
    @Operation(summary = "delete product", description = "상품을 삭제한다.")
    @DeleteMapping("/product/{id}")
    ApiCommonResponse<Product> deleteProduct(@PathVariable("id") String id){

        Product product = productService.deleteProduct(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK, product);
    }

}
