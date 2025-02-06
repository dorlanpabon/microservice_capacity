package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ICapacityServicePort;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.domain.spi.ITechnologyPersistencePort;
import com.pragma.powerup.domain.usecase.CapacityUseCase;
import com.pragma.powerup.infrastructure.output.http.adapter.TechnologyPersistenceAdapter;
import com.pragma.powerup.infrastructure.output.http.client.TechnologyFeignClient;
import com.pragma.powerup.infrastructure.output.jpa.adapter.CapacityJpaAdapter;
import com.pragma.powerup.infrastructure.output.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ICapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCapacity {
    private final ICapacityRepository technologyRepository;
    private final ICapacityEntityMapper technologyEntityMapper;
    private final TechnologyFeignClient technologyFeignClient;
    private final HttpServletRequest request;

    @Bean
    public ICapacityPersistencePort technologyPersistencePort() {
        return new CapacityJpaAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ICapacityServicePort technologyServicePort() {
        return new CapacityUseCase(technologyPersistencePort(), technologyServiceAdapter());
    }

    @Bean
    public ITechnologyPersistencePort technologyServiceAdapter() {
        return new TechnologyPersistenceAdapter(technologyFeignClient, request);
    }
}