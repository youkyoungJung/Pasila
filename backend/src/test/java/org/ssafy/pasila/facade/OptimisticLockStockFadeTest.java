package org.ssafy.pasila.facade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.order.repository.OrderRepository;
import org.ssafy.pasila.domain.order.facade.OptimisticLockStockFacade;
import org.springframework.boot.test.context.SpringBootTest;
import org.ssafy.pasila.domain.product.dto.ProductOptionFormDto;
import org.ssafy.pasila.domain.product.entity.Category;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import org.ssafy.pasila.domain.product.repository.CategoryRepository;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OptimisticLockStockFacadeTest {

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OptimisticLockStockFacade stockFacade;

    @BeforeEach
    void setUp() {

        Member mem = Member.builder()
                .name("mem")
                .description("mem_desc")
                .channel("channel")
                .email("email")
                .password("password")
                .phone("01011111111")
                .build();

        Category cate = Category.builder()
                .id(1L)
                .name("test_cate")
                .imageUrl("image")
                .build();

        Product p = Product.builder()
                .name("p_name")
                .description("p_desc")
                .thumbnail("p_thumb")
                .isActive(true)
                .member(mem)
                .category(cate)
                .build();

        ProductOption po = ProductOption.builder()
                .name("po_name")
                .stock(5)
                .price(1000)
                .discountPrice(500)
                .product(p)
                .build();

        memberRepository.saveAndFlush(mem);
        categoryRepository.saveAndFlush(cate);
        productRepository.saveAndFlush(p);
        productOptionRepository.saveAndFlush(po);
    }

    @AfterEach
    void tearDown() {

        orderRepository.deleteAll();
        productOptionRepository.deleteAll(); // 테스트 후 데이터 정리
        memberRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();

    }

    @Test
    @DisplayName("Optimistic LOCK 동시에_여러개의_요청")
    public void testOptimisticLockWithMultiRequests() throws InterruptedException {
        int threadCount = 10; //동시접속자 수
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);


        List<ProductOptionFormDto> options = new ArrayList<>();
        options.add(ProductOptionFormDto.builder()
                .id(1L)
                .quantity(1)
                .price(500)
                .build());

        OrderFormDto orderFormDto = OrderFormDto.builder()
                .options(options)
                .memberId(1L)
                .name("test_name")
                .address("test_addr")
                .build();

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockFacade.saveTrade(orderFormDto);

                } catch (InterruptedException e) {
//                    log.info("runtimeExcetption");
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        ProductOption stock = productOptionRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("test 시 문제발생"));

        assertThat(stock.getStock()).isZero();
    }
}
