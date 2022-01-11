package com.github.allisson95.apachekafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
class KafkaConfig {

    @KafkaListener(topics = "bzx-tbc-ape1")
    public void listenBzx(String in) {
        log.info("Receiving message {} from topic {}", in, "bzx-tbc-ape1");
    }

    @KafkaListener(topics = "edgx-tbc-ape1")
    public void listenEdgx(String in) {
        log.info("Receiving message {} from topic {}", in, "edgx-tbc-ape1");
    }

}
