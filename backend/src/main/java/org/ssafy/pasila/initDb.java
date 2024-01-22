package org.ssafy.pasila;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.member.entity.Gender;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;

@Component
@RequiredArgsConstructor
public class initDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            Member member = createMember("test@test.com", "test", "테스트채널", "01012345678");
            em.persist(member);

            Product product = createProduct("띠용");
            em.persist(product);
        }

        private Member createMember(String email, String name, String channel, String phone) {
            Member member = new Member().builder()
                    .email(email)
                    .name(name)
                    .channel(channel)
                    .phone(phone)
                    .gender(Gender.M)
                    .build();
            return member;
        }

        private Product createProduct(String name) {
            Product product = new Product().builder()
                    .name(name)
                    .build();
            return product;
        }

    }
}
