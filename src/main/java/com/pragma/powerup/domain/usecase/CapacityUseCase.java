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
                .flatMap(cap -> {
                    if (cap.getName() == null || cap.getName().length() > 50) {
                        return Mono.error(new DomainException(DomainConstants.NAME_MUST_BE_LESS_THAN_50_CHARACTERS));
                    }
                    if (cap.getDescription() == null || cap.getDescription().length() > 90) {
                        return Mono.error(new DomainException(DomainConstants.DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS));
                    }
                    return capacityPersistencePort.findCapacityByName(cap.getName())
                            .flatMap(existing -> Mono.error(new DomainException(DomainConstants.TECHNOLOGY_ALREADY_EXISTS)))
                            .switchIfEmpty(capacityPersistencePort.saveCapacity(cap)
                                    .flatMap(savedCapacity ->
                                            technologyPersistencePort.saveTechnologiesCapacity(savedCapacity.getId(), cap.getTechnologies())
                                    )
                            );
                })
                .then();
    }


    @Override
    public Flux<Capacity> listCapacities(Integer page, Integer size, String direction) {
        return capacityPersistencePort.listCapacities(page, size, direction);
    }


}