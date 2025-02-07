package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.spi.IBootcampCapacityPersistencePort;
import com.pragma.powerup.infrastructure.output.jpa.mapper.IBootcampCapacityEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.IBootcampCapacityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BootcampCapacityJpaAdapter implements IBootcampCapacityPersistencePort {

    private final IBootcampCapacityRepository bootcampCapacityRepository;
    private final IBootcampCapacityEntityMapper bootcampTechnologyEntityMapper;

    @Override
    public Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities) {
        return Flux.fromIterable(capacities)
                .map(technologyId -> bootcampTechnologyEntityMapper.toEntity(bootcampId, technologyId))
                .flatMap(bootcampCapacityRepository::save)
                .then();
    }

}
