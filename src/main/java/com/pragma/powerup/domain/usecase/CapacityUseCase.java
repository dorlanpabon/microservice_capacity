package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ICapacityServicePort;
import com.pragma.powerup.domain.constants.DomainConstants;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapacityUseCase implements ICapacityServicePort {

    private final ICapacityPersistencePort technologyPersistencePort;

    public CapacityUseCase(ICapacityPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Void> saveTechnology(Capacity capacity) {
        return Mono.just(capacity)
                .flatMap(tech -> {
                    if (tech.getName() == null || tech.getName().length() > 50) {
                        return Mono.error(new DomainException(DomainConstants.NAME_MUST_BE_LESS_THAN_50_CHARACTERS));
                    }
                    if (tech.getDescription() == null || tech.getDescription().length() > 90) {
                        return Mono.error(new DomainException(DomainConstants.DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS));
                    }
                    return technologyPersistencePort.findTechnologyByName(tech.getName());
                })
                .flatMap(tech -> Mono.error(new DomainException(DomainConstants.TECHNOLOGY_ALREADY_EXISTS)))
                .switchIfEmpty(Mono.defer(() -> technologyPersistencePort.saveTechnology(capacity)))
                .then();
    }

    @Override
    public Flux<Capacity> listTechnologies(Integer page, Integer size, String direction) {
        return technologyPersistencePort.listTechnologies(page, size, direction);
    }


}