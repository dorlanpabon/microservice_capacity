package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityPersistencePort {

    Mono<Capacity> saveCapacity(Capacity capacity);

    Mono<Capacity> findCapacityByName(String name);

    Flux<Capacity> listCapacities(Integer page, Integer size, String direction, String field);
}