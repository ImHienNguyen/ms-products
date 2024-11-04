package com.imhiennguyen.ws.ms_products.service.mapper;

import com.imhiennguyen.ws.ms_products.controller.dto.CreateProductRequest;
import com.imhiennguyen.ws.ms_products.service.dto.ProductCreatedEvent;
import org.mapstruct.*;

import static com.imhiennguyen.ws.ms_products.common.Constants.MAPPER_SPRING_COMPONENT_MODEL;

@Mapper(componentModel = MAPPER_SPRING_COMPONENT_MODEL)
public abstract class ProductCreatedEventMapper {

    @Mapping(target = "productId", ignore = true)
    public abstract ProductCreatedEvent map(CreateProductRequest createProductRequest, @Context String productId);

    @AfterMapping
    protected void mapProductId(@Context String productId, @MappingTarget ProductCreatedEvent productCreatedEvent) {
        productCreatedEvent.setProductId(productId);
    }
}
