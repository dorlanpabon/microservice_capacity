package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IBootcampCapacityServicePort;
import com.pragma.powerup.domain.spi.IBootcampCapacityPersistencePort;
import com.pragma.powerup.domain.usecase.BootcampCapacityUseCase;
import com.pragma.powerup.infrastructure.output.jpa.adapter.BootcampCapacityJpaAdapter;
import com.pragma.powerup.infrastructure.output.jpa.mapper.IBootcampCapacityEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.IBootcampCapacityRepository;
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