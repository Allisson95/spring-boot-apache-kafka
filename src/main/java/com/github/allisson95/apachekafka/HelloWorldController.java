package com.github.allisson95.apachekafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
class HelloWorldController {

    @GetMapping("/")
    public Mono<Map<String, String>> helloWorld() {
        return Mono.just(new HashMap<String, String>())
                .map(attributes -> {
                    attributes.put("message", "Hello World!!!");
                    return attributes;
                });
    }

    public Flux<ResponseEntity<Map<String, Object>>> subscribe() {
        return Flux.empty();
    }

}
