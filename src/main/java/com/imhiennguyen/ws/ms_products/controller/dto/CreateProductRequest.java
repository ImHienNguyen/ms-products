package com.imhiennguyen.ws.ms_products.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateProductRequest {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
