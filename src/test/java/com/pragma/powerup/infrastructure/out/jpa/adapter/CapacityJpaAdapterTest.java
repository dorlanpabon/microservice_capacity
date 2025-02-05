package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.infrastructure.out.jpa.entity.CapacityEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICapacityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class CapacityJpaAdapterTest {
    @Mock
    ICapacityRepository technologyRepository;
    @Mock
    ICapacityEntityMapper technologyEntityMapper;
    @InjectMocks
    CapacityJpaAdapter capacityJpaAdapter;
    @Mock
    Capacity capacity;
    @Mock
    CapacityEntity capacityEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        capacity = new Capacity();
        capacity.setId(1L);
        capacity.setName("name");
        capacity.setDescription("description");

        capacityEntity = new CapacityEntity();
        capacityEntity.setId(1L);
        capacityEntity.setName("name");
        capacityEntity.setDescription("description");
    }

    @Test
    void testSaveTechnology() {
        when(technologyEntityMapper.toEntity(any(Capacity.class))).thenReturn(capacityEntity);
        when(technologyRepository.save(any(CapacityEntity.class))).thenReturn(Mono.just(capacityEntity));
        when(technologyEntityMapper.toTechnology(any(CapacityEntity.class))).thenReturn(capacity);

        Mono<Capacity> result = capacityJpaAdapter.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(technologyEntityMapper, times(1)).toEntity(capacity);
        verify(technologyRepository, times(1)).save(capacityEntity);
    }

    @Test
    void testFindTechnologyByName() {
        when(technologyRepository.findByName(anyString())).thenReturn(Mono.empty());

        Mono<Capacity> result = capacityJpaAdapter.findTechnologyByName("name");

        StepVerifier.create(result)
                .verifyComplete();

        verify(technologyRepository, times(1)).findByName("name");
    }

    @Test
    void testListTechnologies() {
        when(technologyRepository.findBy(any())).thenReturn(Flux.just(capacityEntity));
        when(technologyEntityMapper.toTechnology(any(CapacityEntity.class))).thenReturn(capacity);

        Flux<Capacity> result = capacityJpaAdapter.listTechnologies(1, 10, "ASC");

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(technologyRepository, times(1)).findBy(any());
    }
}