package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityServicePort {

    Mono<Void> saveTechnology(Capacity capacity);

    Flux<Capacity> listTechnologies(Integer page, Integer size, String direction);
}