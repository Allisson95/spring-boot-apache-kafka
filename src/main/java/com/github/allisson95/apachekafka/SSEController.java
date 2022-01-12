package com.github.allisson95.apachekafka;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
class SSEController {

    private final SSEClientEventHandler eventHandler;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> events() {
        Client subscribedClient = eventHandler.addClient();

        return subscribedClient
                .toFlux()
                .doOnCancel(() -> {
                    log.debug("Subscribe finished for client {}", subscribedClient.getId().toString());
                    eventHandler.removeClient(subscribedClient.getId());
                })
                .doOnComplete(() -> {
                    log.debug("Subscribe finished for client {}", subscribedClient.getId().toString());
                    eventHandler.removeClient(subscribedClient.getId());
                });
    }

}
