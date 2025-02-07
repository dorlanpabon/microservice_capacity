package com.pragma.powerup.infrastructure.entrypoints.handler;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IBootcampCapacityHandler {

    Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities);

}