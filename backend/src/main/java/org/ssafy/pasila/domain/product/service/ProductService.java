package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.entity.*;
import org.ssafy.pasila.domain.product.repository.CategoryRepository;
import org.ssafy.pasila.domain.product.repository.ProductJoinRepository;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    private final CategoryRepository categoryRepository;
    private final ProductOptionRepository productOptionRepository;
    private final S3Uploader s3Uploader;

    //상품 등록
    @Transactional
    public void saveProduct(ProductRequest productRequest, MultipartFile image) throws IOException {

        Product savedProduct = productRequest.getProduct();
        Category category = categoryRepository.findById(productRequest.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 카테고리가 없습니다."));
        // 카테고리 저장
        savedProduct.addProductWithCategory(category);

        List<ProductOption> productOptions = productRequest.getProductOptions();
        for(ProductOption option : productOptions){
            option.addProduct(savedProduct);
            productOptionRepository.save(option);
        }
        // repository를 통한 저장
        productRepository.save(savedProduct);
        // 이미지 처리 메서드 호출
        handleImage(savedProduct, image);
    }

    // 이미지 처리 메서드
    private void handleImage(Product product, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            log.info("product:{}", product);
            String storedFileName = s3Uploader.upload(product.getId(), image, "images");
            product.addThumbnailUrl(storedFileName);
        }
    }

    //상품 수정
    @Transactional
    public void updateProduct(String id, ProductRequest productRequest, MultipartFile newImageFile) throws IOException {

        Product result = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 상품이 없습니다."));


        Category category = categoryRepository.findById(productRequest.getCategory().getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 아이디에 대한 카테고리가 없습니다."));

        List<ProductOption> productOptions = productRequest.getProductOptions();
        for(ProductOption option : productOptions){
            ProductOption update = productOptionRepository.findById(option.getId())
                    .orElseThrow(()->new IllegalArgumentException("option id 가 없습니다"));
            update.updateProductOption(option);
        }

        result.updateProduct(productRequest.getProduct(), category);
        //새로운 이미지 변경
        String originImageUrl = result.getThumbnail();
        log.info("originImageUrl:{}", originImageUrl);
        if (!newImageFile.isEmpty()) {  //imageUrl이 있을 경우 S3접근
            //imageUrl 로 S3에 있는 originalFilename 접근을 위한 split 사용
            String splitStr = ".com/";
            String fileName = originImageUrl.substring(originImageUrl.lastIndexOf(splitStr) + splitStr.length());
            s3Uploader.deleteImage(fileName);
            log.info("success: deleteImage 수행");
            handleImage(result, newImageFile);
        }

        log.info("반영 result : {}", result);
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(String id){

        String originImageUrl = "";
        originImageUrl = findProductImageUrl(id);

        log.info("originImageUrl: {}", originImageUrl);

        if (!originImageUrl.isEmpty()) {  //imageUrl이 있을 경우 S3접근
            //imageUrl 로 S3에 있는 originalFilename 접근을 위한 split 사용
            String splitStr = ".com/";
            String fileName = originImageUrl.substring(originImageUrl.lastIndexOf(splitStr) + splitStr.length());
            s3Uploader.deleteImage(fileName);
            log.info("success: deleteImage 수행");
        }

        productRepository.deleteById(id);

    }

    //상품 이미지 url 찾기
    public String findProductImageUrl(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id에 해당하는 Product 가 없습니다."));
        return product.getThumbnail();
    }

}
