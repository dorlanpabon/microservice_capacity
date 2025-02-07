package com.pragma.powerup.application.config;

import com.pragma.powerup.domain.api.IBootcampCapacityServicePort;
import com.pragma.powerup.domain.spi.IBootcampCapacityPersistencePort;
import com.pragma.powerup.domain.usecase.BootcampCapacityUseCase;
import com.pragma.powerup.infrastructure.adapters.jpa.adapter.BootcampCapacityJpaAdapter;
import com.pragma.powerup.infrastructure.adapters.jpa.mapper.IBootcampCapacityEntityMapper;
import com.pragma.powerup.infrastructure.adapters.jpa.repository.IBootcampCapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBootcampCapacity {
    private final IBootcampCapacityRepository capacityTechnologyRepository;
    private final IBootcampCapacityEntityMapper categoryTechnologyEntityMapper;

    @Bean
    public IBootcampCapacityPersistencePort capacityTechnologyPersistencePort() {
        return new BootcampCapacityJpaAdapter(capacityTechnologyRepository, categoryTechnologyEntityMapper);
    }

    @Bean
    public IBootcampCapacityServicePort capacityTechnologyServicePort() {
        return new BootcampCapacityUseCase(capacityTechnologyPersistencePort());
    }

}