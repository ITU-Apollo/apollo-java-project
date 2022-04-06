package com.apollo.mongoapi.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafKaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String topic) {
        log.info(String.format("Message sent -> "));
        this.kafkaTemplate.send(topic, message);
    }
}