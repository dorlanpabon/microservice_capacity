package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ICapacityServicePort;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.domain.usecase.CapacityUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.CapacityJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCapacity {
    private final ICapacityRepository technologyRepository;
    private final ICapacityEntityMapper technologyEntityMapper;

    @Bean
    public ICapacityPersistencePort technologyPersistencePort() {
        return new CapacityJpaAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ICapacityServicePort technologyServicePort() {
        return new CapacityUseCase(technologyPersistencePort());
    }
}