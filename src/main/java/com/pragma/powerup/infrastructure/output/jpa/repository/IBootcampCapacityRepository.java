package com.pragma.powerup.infrastructure.output.jpa.repository;

import com.pragma.powerup.infrastructure.output.jpa.entity.BootcampCapacityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IBootcampCapacityRepository extends ReactiveCrudRepository<BootcampCapacityEntity, Long> {

}