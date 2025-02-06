package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.infrastructure.output.jpa.entity.CapacityEntity;
import com.pragma.powerup.infrastructure.output.jpa.mapper.ICapacityEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ICapacityRepository;
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
    ICapacityRepository capacityRepository;
    @Mock
    ICapacityEntityMapper capacityEntityMapper;
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
    void testsaveCapacity() {
        when(capacityEntityMapper.toEntity(any(Capacity.class))).thenReturn(capacityEntity);
        when(capacityRepository.save(any(CapacityEntity.class))).thenReturn(Mono.just(capacityEntity));
        when(capacityEntityMapper.toCapacity(any(CapacityEntity.class))).thenReturn(capacity);

        Mono<Capacity> result = capacityJpaAdapter.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(capacityEntityMapper, times(1)).toEntity(capacity);
        verify(capacityRepository, times(1)).save(capacityEntity);
    }

    @Test
    void testfindCapacityByName() {
        when(capacityRepository.findByName(anyString())).thenReturn(Mono.empty());

        Mono<Capacity> result = capacityJpaAdapter.findCapacityByName("name");

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityRepository, times(1)).findByName("name");
    }

    @Test
    void testlistCapacities() {
        when(capacityRepository.findBy(any())).thenReturn(Flux.just(capacityEntity));
        when(capacityEntityMapper.toCapacity(any(CapacityEntity.class))).thenReturn(capacity);

        Flux<Capacity> result = capacityJpaAdapter.listCapacities(1, 10, "ASC");

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(capacityRepository, times(1)).findBy(any());
    }
}