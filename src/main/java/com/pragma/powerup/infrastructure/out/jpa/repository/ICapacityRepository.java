package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.CapacityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ICapacityRepository extends ReactiveCrudRepository<CapacityEntity, Long> {

    Mono<CapacityEntity> findByName(String name);

    Flux<CapacityEntity> findBy(Pageable pageable);

}