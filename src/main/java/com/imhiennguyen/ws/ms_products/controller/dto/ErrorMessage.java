package com.imhiennguyen.ws.ms_products.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ErrorMessage {
    private ZonedDateTime timestamp;
    private String message;
    private String details;
}
