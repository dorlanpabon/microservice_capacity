package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.infrastructure.output.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ICapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CapacityJpaAdapter implements ICapacityPersistencePort {

    private final ICapacityRepository technologyRepository;
    private final ICapacityEntityMapper technologyEntityMapper;

    @Override
    public Mono<Capacity> saveCapacity(Capacity capacity) {
        return Mono.just(technologyEntityMapper.toEntity(capacity))
                .flatMap(technologyRepository::save)
                .map(technologyEntityMapper::toCapacity);
    }

    @Override
    public Mono<Capacity> findCapacityByName(String name) {
        return technologyRepository.findByName(name)
                .map(technologyEntityMapper::toCapacity);
    }

    @Override
    public Flux<Capacity> listCapacities(Integer page, Integer size, String direction, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), field);

        return technologyRepository.findBy(pageable)
                        .map(technologyEntityMapper::toCapacity);
    }
}