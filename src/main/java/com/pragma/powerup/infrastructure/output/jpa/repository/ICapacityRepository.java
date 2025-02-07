package com.pragma.powerup.infrastructure.output.jpa.repository;

import com.pragma.powerup.infrastructure.output.jpa.entity.CapacityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ICapacityRepository extends ReactiveCrudRepository<CapacityEntity, Long> {

    Mono<CapacityEntity> findByName(String name);

    Flux<CapacityEntity> findBy(Pageable pageable);

    @Query("SELECT * FROM capacities INNER JOIN bootcamp_capacity ON capacities.id = bootcamp_capacity.capacity_id WHERE bootcamp_capacity.bootcamp_id = :bootcampId")
    Mono<CapacityEntity> findByBootcampId(Long bootcampId);
}