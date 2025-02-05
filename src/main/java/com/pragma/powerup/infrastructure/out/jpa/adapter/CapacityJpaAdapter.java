package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICapacityRepository;
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
    public Mono<Capacity> saveTechnology(Capacity capacity) {
        return Mono.just(technologyEntityMapper.toEntity(capacity))
                .flatMap(technologyRepository::save)
                .map(technologyEntityMapper::toTechnology);
    }

    @Override
    public Mono<Capacity> findTechnologyByName(String name) {
        return technologyRepository.findByName(name)
                .map(technologyEntityMapper::toTechnology);
    }

    @Override
    public Flux<Capacity> listTechnologies(Integer page, Integer size, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), "name");

        return technologyRepository.findBy(pageable)
                        .map(technologyEntityMapper::toTechnology);
    }
}