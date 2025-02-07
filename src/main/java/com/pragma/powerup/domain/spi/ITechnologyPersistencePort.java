package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {

    Mono<Boolean> saveTechnologiesCapacity(Long capacityId, List<Long> technologies);

    Flux<Technology> findTechnologiesByCapacity(Long id);
}
