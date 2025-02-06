package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ICapacityServicePort;
import com.pragma.powerup.domain.constants.DomainConstants;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapacityUseCase implements ICapacityServicePort {

    private final ICapacityPersistencePort capacityPersistencePort;
    private final ITechnologyPersistencePort technologyPersistencePort;

    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort, ITechnologyPersistencePort technologyPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Void> saveCapacity(Capacity capacity) {
        return Mono.justOrEmpty(capacity)
                .filter(cap -> cap.getName() != null && cap.getName().length() <= 50)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.NAME_MUST_BE_LESS_THAN_50_CHARACTERS)))
                .filter(cap -> cap.getDescription() != null && cap.getDescription().length() <= 90)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS)))
                .filter(cap -> cap.getTechnologies() != null && !cap.getTechnologies().isEmpty())
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.EMPTY_TECHNOLOGY_LIST)))
                .filter(cap -> cap.getTechnologies().size() >= DomainConstants.MIN_TECHNOLOGIES && cap.getTechnologies().size() <= DomainConstants.MAX_TECHNOLOGIES)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_TECHNOLOGY_COUNT)))
                .flatMap(validCap -> capacityPersistencePort.findCapacityByName(validCap.getName()))
                .flatMap(existingCap -> Mono.error(new DomainException(DomainConstants.CAPACITY_ALREADY_EXISTS)))
                .switchIfEmpty(Mono.defer(() -> capacityPersistencePort.saveCapacity(capacity))
                        .flatMap(savedCap ->
                                technologyPersistencePort.saveTechnologiesCapacity(savedCap.getId(), capacity.getTechnologies())
                        )
                )
                .then();
    }


    @Override
    public Flux<Capacity> listCapacities(Integer page, Integer size, String direction) {
        return capacityPersistencePort.listCapacities(page, size, direction);
    }


}