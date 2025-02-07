package com.pragma.powerup.domain.api;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IBootcampCapacityServicePort {

    Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities);

}