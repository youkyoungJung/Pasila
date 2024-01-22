package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.product.dto.product.ProductRequest;
import org.ssafy.pasila.domain.product.entity.DetailCategory;
import org.ssafy.pasila.domain.product.entity.LargeCategory;
import org.ssafy.pasila.domain.product.entity.MiddleCategory;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductJoinRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;
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
    private final S3Uploader s3Uploader;

    //상품 등록
    @Transactional
    public void saveProduct(ProductRequest productRequest, MultipartFile image) throws IOException {

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

        if(!image.isEmpty()){
            String storedFileName = s3Uploader.upload(image, "images");
            savedProduct.setThumbnail(storedFileName);
        }

        // repository를 통한 저장
        productRepository.save(savedProduct);

    }

    //상품 수정
    //TODO : 0. 파일 수정권 변경
    @Transactional
    public void updateProduct(Long id, ProductRequest productRequest, String deleteImageName, MultipartFile newImageFile) throws IOException {

        LargeCategory largeCategory = new LargeCategory(productRequest.getLargeCategoryId());
        MiddleCategory middleCategory = new MiddleCategory(productRequest.getMiddleCategoryId());
        DetailCategory detailCategory = new DetailCategory(productRequest.getDetailCategoryId());
        Product result = productJoinRepository.findOne(id);

        if(deleteImageName != null && !deleteImageName.isEmpty()){
            log.info("deleteImageName이 들어왔습니다.");
            s3Uploader.deleteImage(deleteImageName);
            result.setThumbnail("");
        }
        if(newImageFile != null && !newImageFile.isEmpty()){
            log.info("newImageFile이 들어왔습니다.");
            String storedFileName = s3Uploader.upload(newImageFile, "images");
            result.setThumbnail(storedFileName);
        }

        result.setName(productRequest.getProduct().getName());
        result.setDescription(productRequest.getProduct().getDescription());
        result.setUpdatedAt(LocalDateTime.now());
        result.setLargeCategory(largeCategory);
        result.setMiddleCategory(middleCategory);
        result.setDetailCategory(detailCategory);
        log.info("반영 result : {}", result);
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(Long id){

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
    public String findProductImageUrl(Long id){
        Optional<Product> product = productRepository.findById(id);
        String result = product.get().getThumbnail();
        return result;
    }


}
