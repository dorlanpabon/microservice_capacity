package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class CapacityUseCaseTest {
    @Mock
    ICapacityPersistencePort technologyPersistencePort;

    @InjectMocks
    CapacityUseCase capacityUseCase;

    private Capacity capacity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacity = new Capacity();
        capacity.setId(1L);
        capacity.setName("name");
        capacity.setDescription("description");
    }

    @Test
    void testSaveTechnology_Success() {
        when(technologyPersistencePort.findTechnologyByName(anyString())).thenReturn(Mono.empty());
        when(technologyPersistencePort.saveTechnology(any(Capacity.class))).thenReturn(Mono.just(capacity));

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).findTechnologyByName("name");
        verify(technologyPersistencePort, times(1)).saveTechnology(capacity);
    }

    @Test
    void testSaveTechnology_AlreadyExists() {
        when(technologyPersistencePort.findTechnologyByName(any())).thenReturn(Mono.just(capacity));

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(technologyPersistencePort, times(1)).findTechnologyByName("name");
        verify(technologyPersistencePort, never()).saveTechnology(any(Capacity.class));
    }

    @Test
    void testSaveTechnology_NameNull() {
        capacity.setName(null);

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(technologyPersistencePort, never()).findTechnologyByName(anyString());
        verify(technologyPersistencePort, never()).saveTechnology(any(Capacity.class));
    }

    @Test
    void testSaveTechnology_NameTooLong() {
        capacity.setName("a".repeat(51));

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(technologyPersistencePort, never()).findTechnologyByName(anyString());
        verify(technologyPersistencePort, never()).saveTechnology(any(Capacity.class));
    }

    @Test
    void testSaveTechnology_DescriptionNull() {
        capacity.setDescription(null);

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(technologyPersistencePort, never()).findTechnologyByName(anyString());
        verify(technologyPersistencePort, never()).saveTechnology(any(Capacity.class));
    }

    @Test
    void testSaveTechnology_DescriptionTooLong() {
        capacity.setDescription("a".repeat(91));

        Mono<Void> result = capacityUseCase.saveTechnology(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(technologyPersistencePort, never()).findTechnologyByName(anyString());
        verify(technologyPersistencePort, never()).saveTechnology(any(Capacity.class));
    }

    @Test
    void testListTechnologies() {
        when(technologyPersistencePort.listTechnologies(anyInt(), anyInt(), anyString())).thenReturn(Flux.just(capacity));

        Flux<Capacity> result = capacityUseCase.listTechnologies(1, 10, "ASC");

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).listTechnologies(1, 10, "ASC");
    }
}
