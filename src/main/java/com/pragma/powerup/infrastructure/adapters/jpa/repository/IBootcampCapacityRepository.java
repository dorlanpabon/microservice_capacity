package com.pragma.powerup.infrastructure.adapters.jpa.repository;

import com.pragma.powerup.infrastructure.adapters.jpa.entity.BootcampCapacityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IBootcampCapacityRepository extends ReactiveCrudRepository<BootcampCapacityEntity, Long> {

}