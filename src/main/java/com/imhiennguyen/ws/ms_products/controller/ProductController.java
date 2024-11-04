package com.imhiennguyen.ws.ms_products.controller;

import com.imhiennguyen.ws.ms_products.controller.dto.CreateProductRequest;
import com.imhiennguyen.ws.ms_products.controller.dto.ErrorMessage;
import com.imhiennguyen.ws.ms_products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/products") // http://localhost:<port>/products
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRequest request) {
        String productId;
        try {
            productId = productService.createProduct(request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .timestamp(ZonedDateTime.now())
                    .message(e.getMessage())
                    .details("/products")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
