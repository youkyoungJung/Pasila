package org.ssafy.pasila.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import org.ssafy.pasila.domain.product.dto.ProductRequestDto;
import org.ssafy.pasila.domain.product.dto.ProductResponseDto;
import org.ssafy.pasila.domain.product.dto.ProductSellResponseDto;
import org.ssafy.pasila.domain.product.entity.*;
import org.ssafy.pasila.domain.product.repository.CategoryRepository;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
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

    private final LiveRepository liveRepository;

    private final S3Uploader s3Uploader;

    // 상품 저장 서비스
    @Transactional
    public String saveProduct(ProductRequestDto productRequestDto, MultipartFile image) throws IOException {

        Category category = getCategoryById(productRequestDto.getCategoryId());

        Member member = memberRepository.findById(productRequestDto.getMemberId())
                .orElseThrow(()-> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));

        //옵션 처리
        Product product = Product.createProduct(productRequestDto, member, category);
        productRepository.save(product);
        saveProductOptions(product.getId(), productRequestDto.getOptions());
        handleImage(product.getId(), image); // S3 접근 -> url 저장
        return product.getId();

    }

    // 상품 수정 서비스
    @Transactional
    public String updateProduct(String id, ProductRequestDto productRequestDto, MultipartFile newImageFile) throws IOException {

        Product result = getProductById(id); //prodcut 접근
        Category category = getCategoryById(productRequestDto.getCategoryId()); //카테고리 접근
        updateProductOptions(productRequestDto.getOptions());
        result.updateProduct(productRequestDto, category);
        handleNewImage(result.getId(), newImageFile);
        return result.getId();

    }

    // 상품 삭제 서비스
    @Transactional
    public String deleteProduct(String id) {

        Product product = getProductById(id);
        deleteImageIfExists(product.getThumbnail());
        product.setActive(false);
        return product.getId();

    }

    public ProductResponseDto getProduct(String id){
        Product product = getProductById(id);
        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(id)
                .stream()
                .map(ProductOptionDto::new)
                .toList();

        return ProductResponseDto.builder()
                .id(product.getId())
                .sellerId(product.getMember().getId())
                .name(product.getName())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .thumbnail(product.getThumbnail())
                .categoryId(product.getCategory().getId())
                .options(options)
                .build();

    }

    /**
     * 상품을 찾는 메서드
     */
    public Product getProductById(String id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

    }

    /**카테고리 정보를 찾는 메서드*/
    private Category getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RestApiException(ErrorCode.BAD_REQUEST));

    }

    /** 상품 옵션 정보를 저장하는 메서드*/
    private void saveProductOptions(String productId, List<ProductOptionDto> productOptions) {

        Product product = getProductById(productId);

        productOptions.forEach(option -> {
            ProductOption productOption = ProductOption.createProductOption(product, option);
            productOptionRepository.save(productOption);
        });

    }

    /**
     * 이미지가 있을 경우 S3Upload에 접근, upload
     * 반환된 url을 바탕으로 productThumbnail에 저장
     * */
    private void handleImage(String productId, MultipartFile image) throws IOException {

        Product product = getProductById(productId);
        if (!image.isEmpty()) {
            String storedFileName = s3Uploader.upload(product.getId(), image, "images");
            product.addThumbnailUrl(storedFileName);
        }

    }

    /**
     * 상품 정보를 찾고, 수정된 내용 저장
     * */
    private void updateProductOptions(List<ProductOptionDto> productOptions) {

        for(ProductOptionDto option :  productOptions){
            ProductOption update = productOptionRepository.findById(option.getId())
                    .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
            update.updateProductOption(option);
        }

    }

    /**
     * 새로운 이미지의 수정이 필요할 경우
     * 이미지를 삭제하고 새로운 이미지를 등록시킴
     * */
    private void handleNewImage(String productId, MultipartFile newImageFile) throws IOException {

        Product product = getProductById(productId);
        String originImageUrl = product.getThumbnail();
        if (!newImageFile.isEmpty()) {
            deleteImageIfExists(originImageUrl);
            handleImage(productId, newImageFile);
        }

    }

    /**
     * Thumbnail 은 하나이므로 만약 사진이 수정될 경우
     * S3에서의 기존 사진이 삭제되어야함.
     * */
    private void deleteImageIfExists(String imageUrl) {

        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = extractFileName(imageUrl);
            s3Uploader.deleteImage(fileName);
        }

    }

    /**
     * S3에 저장된 이미지 url은 https://amazon.~~~com/이미지파일명
     * 으로 저장되어있기 때문에 .com/ 을 바탕으로
     * substring 함수를 이용하여 url을 가공시켜 이미지파일명을 추출함.
     * */
    private String extractFileName(String imageUrl) {

        String splitStr = ".com/";
        return imageUrl.substring(imageUrl.lastIndexOf(splitStr) + splitStr.length());

    }

    public ProductSellResponseDto getProductSell(String id) {
        Live live = liveRepository.findByProduct_Id(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        Product product = getProductById(id);
        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(id)
                .stream()
                .map(ProductOptionDto::new)
                .toList();

        ProductSellResponseDto result = ProductSellResponseDto.builder()
                .id(product.getId())
                .sellerId(product.getMember().getId())
                .name(product.getName())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .thumbnail(product.getThumbnail())
                .categoryId(product.getCategory().getId())
                .options(options)
                .bank(product.getMember().getBank())
                .account(product.getMember().getAccount())
                .script(live.getScript())
                .title(live.getTitle())
                .fullVideoUrl(live.getFullVideoUrl())
                .isOn(live.isOn())
                .build();

        return result;
    }
}