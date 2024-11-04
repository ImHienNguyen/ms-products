package com.imhiennguyen.ws.ms_products.service.impl;

import com.imhiennguyen.ws.core.ProductCreatedEvent;
import com.imhiennguyen.ws.ms_products.controller.dto.CreateProductRequest;
import com.imhiennguyen.ws.ms_products.service.ProductService;
import com.imhiennguyen.ws.ms_products.service.mapper.ProductCreatedEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${services.products.kafka.topic.name}")
    private String topicName;

    private final ProductCreatedEventMapper productCreatedEventMapper;
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductRequest createProductRequest) throws Exception {
        String productId = UUID.randomUUID().toString();
        // TODO: Persist Product Details into database table before publishing an Event
        ProductCreatedEvent productCreatedEvent = productCreatedEventMapper.map(createProductRequest, productId);

        log.info("Before publishing a ProductCreatedEvent");

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send(topicName, productId, productCreatedEvent).get();

        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Offset: {}", result.getRecordMetadata().offset());

        log.info("Returning product id");
        return productId;
    }
}
