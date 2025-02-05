package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityPersistencePort {

    Mono<Capacity> saveTechnology(Capacity capacity);

    Mono<Capacity> findTechnologyByName(String name);

    Flux<Capacity> listTechnologies(Integer page, Integer size, String direction);
}