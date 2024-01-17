package org.ssafy.pasila.domain.product.controller;

import com.sun.net.httpserver.Authenticator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public CreateProductResponse createProduct(@RequestBody Product product){
        Long id = productService.saveProduct(product);
        return new CreateProductResponse(id);
    }

    @Data
    static public class CreateProductResponse{
        private Long id;
        public  CreateProductResponse(Long id){
            this.id = id;
        }
    }

}
