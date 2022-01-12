package com.github.allisson95.apachekafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Configuration
class KafkaConfig {

    private static final String TOPIC = "teste";

    private final SSEClientEventHandler eventHandler;

    @KafkaListener(topics = KafkaConfig.TOPIC)
    public void listen(String message) {
        log.info("Receiving message {} from topic {}", message, KafkaConfig.TOPIC);
        eventHandler.emitEvent(message);
    }

}
