package com.pragma.powerup.domain.spi;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IBootcampCapacityPersistencePort {

    Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities);

}