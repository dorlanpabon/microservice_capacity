package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityServicePort {

    Mono<Void> saveCapacity(Capacity capacity);

    Flux<Capacity> listCapacities(Integer page, Integer size, String direction);
}