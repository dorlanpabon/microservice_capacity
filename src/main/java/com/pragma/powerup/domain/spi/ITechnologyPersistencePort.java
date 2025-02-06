package com.pragma.powerup.domain.spi;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {

    Mono<Boolean> saveTechnologiesCapacity(Long capacityId, List<Long> technologies);

}
