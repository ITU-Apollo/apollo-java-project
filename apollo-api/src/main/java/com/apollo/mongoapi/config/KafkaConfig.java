package com.apollo.mongoapi.config;

import com.apollo.mongoapi.constant.KafkaConstants;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic pythonTopic() {
        return TopicBuilder.name(KafkaConstants.PYTHON_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic cTopic() {
        return TopicBuilder.name(KafkaConstants.C_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic cppTopic() {
        return TopicBuilder.name(KafkaConstants.CPP_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic goTopic() {
        return TopicBuilder.name(KafkaConstants.GOLANG_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic javaTopic() {
        return TopicBuilder.name(KafkaConstants.JAVA_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic javascriptTopic() {
        return TopicBuilder.name(KafkaConstants.JAVASCRIPT_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic jsonTopic() {
        return TopicBuilder.name(KafkaConstants.JSON_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic jupyterTopic() {
        return TopicBuilder.name(KafkaConstants.JUPYTER_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic markdownTopic() {
        return TopicBuilder.name(KafkaConstants.MARKDOWN_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic rubyTopic() {
        return TopicBuilder.name(KafkaConstants.RUBY_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic rustTopic() {
        return TopicBuilder.name(KafkaConstants.RUST_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic shellTopic() {
        return TopicBuilder.name(KafkaConstants.SHELL_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic yamlTopic() {
        return TopicBuilder.name(KafkaConstants.YAML_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }

    @Bean
    public NewTopic undefinedTopic() {
        return TopicBuilder.name(KafkaConstants.UNDEFINED_TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "21600000")
                .build();
    }
}
