package com.imhiennguyen.ws.ms_products.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${services.products.kafka.topic.name}")
    private String topicName;

    @Value("${services.products.kafka.topic.partition}")
    private Integer partitions;

    @Value("${services.products.kafka.topic.replicas}")
    private Integer replicas;

    @Value("${services.products.kafka.topic.min.insync.replicas}")
    private String minInsyncReplicas;

    @Bean
    public NewTopic createTopic() {

        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicas)
                .configs(Map.of("min.insync.replicas", minInsyncReplicas))
                .build();
    }
}
