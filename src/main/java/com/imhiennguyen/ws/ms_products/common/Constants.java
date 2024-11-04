package com.imhiennguyen.ws.ms_products.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String MAPPER_SPRING_COMPONENT_MODEL = "spring";
    public static final ZoneId SYSTEM_TIME_ZONE = ZoneId.systemDefault();
}
