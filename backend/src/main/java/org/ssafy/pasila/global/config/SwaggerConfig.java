package org.ssafy.pasila.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                .title("Pasila Spring Boot REST API")
                .description("파는게 쉬운 라이브 서비스 '파시라'의 swagger api 입니다." + "\n상품을 등록하고, 숏핑을 지원합니다.")
                .version("1.0.0"));

    }

}