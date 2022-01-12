package com.github.allisson95.apachekafka;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

class Client {

    private UUID id;
    private Flux<String> events;
    private FluxSink<String> emitter;

    public Client() {
        this.id = UUID.randomUUID();
        this.events = Flux.create(e -> this.emitter = e);
    }

    public UUID getId() {
        return id;
    }

    public Flux<String> toFlux() {
        return this.events;
    }

    public void emitEvent(String event) {
        this.emitter.next(event);
    }

}

@Log4j2
@Component
class SSEClientEventHandler {

    private static final ConcurrentMap<UUID, Client> CLIENTS = new ConcurrentHashMap<>();

    public Client addClient() {
        Client client = new Client();
        log.info("Add new client with id {}", client.getId().toString());
        CLIENTS.putIfAbsent(client.getId(), client);
        return client;
    }

    public void removeClient(UUID id) {
        log.info("Remove client with id {}", id.toString());
        CLIENTS.remove(id);
    }

    public void emitEvent(String event) {
        CLIENTS.forEach((id, client) -> {
            log.info("Emit event to client {}", id.toString());
            client.emitEvent(event);
        });
    }

}
