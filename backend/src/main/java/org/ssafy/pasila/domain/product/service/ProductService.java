package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.dto.ProductRequest;
import org.ssafy.pasila.domain.product.entity.*;
import org.ssafy.pasila.domain.product.repository.CategoryRepository;
import org.ssafy.pasila.domain.product.repository.MemberRepository;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductOptionRepository productOptionRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    // 상품 저장 서비스
    @Transactional
    public void saveProduct(ProductRequest productRequest, MultipartFile image) throws IOException {
        Product savedProduct = saveProductInfo(productRequest);
        saveProductOptions(savedProduct, productRequest.getProductOptions());
        productRepository.save(savedProduct);
        handleImage(savedProduct, image);

//        return savedProduct;
    }

    // 상품 수정 서비스
    @Transactional
    public Product updateProduct(String id, ProductRequest productRequest, MultipartFile newImageFile) throws IOException {
        Product result = getProductById(id);
        Category category = getCategoryById(productRequest.getCategory().getId());
        updateProductOptions(productRequest.getProductOptions());
        result.updateProduct(productRequest.getProduct(), category);
        handleNewImage(result, newImageFile);
        log.info("반영 result : {}", result);
        return result;
    }

    // 상품 삭제 서비스
    @Transactional
    public Product deleteProduct(String id) {
        Product product = getProductById(id);
        deleteImageIfExists(product.getThumbnail());
        product.setActive(false);
        return product;
    }

    //== 서비스에 필요한 관련 메서드 작성 ==//
    /**상품 정보를 카테고리와 함께 저장하는 메서드*/
    private Product saveProductInfo(ProductRequest productRequest) {
        Product savedProduct = productRequest.getProduct();
        Category category = getCategoryById(productRequest.getCategory().getId());
        Member seller = getMemberById(productRequest.getMember().getId());
        savedProduct.addProductWithCategoryWithMember(category, seller);
        return savedProduct;
    }

    /**
     * 상품을 찾는 메서드
     */
    private Product getProductById(String id) {
        return productRepository.findById(id)
//                .orElseThrow();
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 상품이 없습니다."));
    }

    /** 멤버를 찾는 메서드*/
    private Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow();
//                .orElseThrow(()-> new IllegalArgumentException("해당 아이디에 대한 멤버정보가 없습니다."));
    }

    /**카테고리 정보를 찾는 메서드*/
    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 카테고리가 없습니다."));
    }

    /** 상품 옵션 정보를 저장하는 메서드*/
    private void saveProductOptions(Product savedProduct, List<ProductOption> productOptions) {
        productOptions.forEach(option -> {
            option.addProduct(savedProduct);
            productOptionRepository.save(option);
        });
    }


    // 이미지 처리 메서드
    /**
     * 이미지가 있을 경우 S3Upload에 접근, upload
     * 반환된 url을 바탕으로 productThumbnail에 저장
     * */
    private void handleImage(Product product, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            log.info("product:{}", product);
            String storedFileName = s3Uploader.upload(product.getId(), image, "images");
            product.addThumbnailUrl(storedFileName);
        }
    }


    //상품 옵션을 수정하는 메서드
    /**
     * 상품 정보를 찾고, 수정된 내용 저장
     * */
    private void updateProductOptions(List<ProductOption> productOptions) {
        productOptions.forEach(option -> {
            ProductOption update = productOptionRepository.findById(option.getId())
                    .orElseThrow(() -> new IllegalArgumentException("option id 가 없습니다"));
            update.updateProductOption(option);
        });
    }

    /**
     * 새로운 이미지의 수정이 필요할 경우
     * 이미지를 삭제하고 새로운 이미지를 등록시킴
     * */
    private void handleNewImage(Product product, MultipartFile newImageFile) throws IOException {
        String originImageUrl = product.getThumbnail();
        log.info("originImageUrl: {}", originImageUrl);
        if (!newImageFile.isEmpty()) {
            deleteImageIfExists(originImageUrl);
            handleImage(product, newImageFile);
        }
    }

    // 이미지가 존재할 경우 삭제하는 경우
    /**
     * Thumbnail 은 하나이므로 만약 사진이 수정될 경우
     * S3에서의 기존 사진이 삭제되어야함.
     * */
    private void deleteImageIfExists(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = extractFileName(imageUrl);
            s3Uploader.deleteImage(fileName);
            log.info("success: deleteImage 수행");
        }
    }

    //imageFile 이름을 추출하는 메서드
    /**
     * S3에 저장된 이미지 url은 https://amazon.~~~com/이미지파일명
     * 으로 저장되어있기 때문에 .com/ 을 바탕으로
     * substring 함수를 이용하여 url을 가공시켜 이미지파일명을 추출함.
     * */
    private String extractFileName(String imageUrl) {
        String splitStr = ".com/";
        return imageUrl.substring(imageUrl.lastIndexOf(splitStr) + splitStr.length());
    }
}
