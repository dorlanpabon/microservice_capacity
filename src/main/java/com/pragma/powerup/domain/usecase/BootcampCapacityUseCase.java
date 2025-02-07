package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IBootcampCapacityServicePort;
import com.pragma.powerup.domain.constants.DomainConstants;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.spi.IBootcampCapacityPersistencePort;
import reactor.core.publisher.Mono;

import java.util.List;

public class BootcampCapacityUseCase implements IBootcampCapacityServicePort {

    private final IBootcampCapacityPersistencePort bootcampCapacityPersistencePort;

    public BootcampCapacityUseCase(IBootcampCapacityPersistencePort bootcampCapacityPersistencePort) {
        this.bootcampCapacityPersistencePort = bootcampCapacityPersistencePort;
    }

    @Override
    public Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities) {
        return Mono.justOrEmpty(bootcampId)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_BOOTCAMP_ID)))
                .then(Mono.justOrEmpty(capacities))
                .filter(list -> !list.isEmpty())
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.EMPTY_CAPACITY_LIST)))
                .filter(list -> list.size() >= DomainConstants.MIN_CAPACITIES && list.size() <= DomainConstants.MAX_CAPACITIES)
                .switchIfEmpty(Mono.error(new DomainException(DomainConstants.INVALID_CAPACITY_COUNT)))
                .flatMap(validList -> bootcampCapacityPersistencePort.saveCapacitiesBootcamp(bootcampId, validList));
    }

}
