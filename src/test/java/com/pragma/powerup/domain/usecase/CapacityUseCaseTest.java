package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.domain.spi.ICapacityPersistencePort;
import com.pragma.powerup.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

class CapacityUseCaseTest {

    @Mock
    ICapacityPersistencePort capacityPersistencePort;

    @Mock
    ITechnologyPersistencePort technologyPersistencePort;

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

        List<Long> technologies = List.of(1L, 2L,3L);

        capacity.setTechnologies(technologies);
    }

    @Test
    void testsaveCapacity_Success() {
        when(capacityPersistencePort.findCapacityByName(anyString())).thenReturn(Mono.empty());
        when(capacityPersistencePort.saveCapacity(any(Capacity.class))).thenReturn(Mono.just(capacity));
        when(technologyPersistencePort.saveTechnologiesCapacity(anyLong(), anyList())).thenReturn(Mono.empty());

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).findCapacityByName("name");
        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
    }

    @Test
    void testsaveCapacity_AlreadyExists() {
        when(capacityPersistencePort.findCapacityByName(any())).thenReturn(Mono.just(capacity));
        when(capacityPersistencePort.saveCapacity(any(Capacity.class))).thenReturn(Mono.just(capacity));

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, times(1)).findCapacityByName("name");
    }

    @Test
    void testsaveCapacity_NameNull() {
        capacity.setName(null);

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_NameTooLong() {
        capacity.setName("a".repeat(51));

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_DescriptionNull() {
        capacity.setDescription(null);

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_DescriptionTooLong() {
        capacity.setDescription("a".repeat(91));

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_TechnologiesNull() {
        capacity.setTechnologies(null);

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_TechnologiesEmpty() {
        capacity.setTechnologies(List.of());

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testsaveCapacity_TechnologiesContainsZero() {
        capacity.setTechnologies(List.of(1L, 0L));

        Mono<Void> result = capacityUseCase.saveCapacity(capacity);

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).findCapacityByName(anyString());
        verify(capacityPersistencePort, never()).saveCapacity(any(Capacity.class));
    }

    @Test
    void testlistCapacities() {
        when(capacityPersistencePort.listCapacities(anyInt(), anyInt(), anyString(), anyString())).thenReturn(Flux.just(capacity));
        when(technologyPersistencePort.findTechnologiesByCapacity(anyLong())).thenReturn(Flux.just());

        Flux<Capacity> result = capacityUseCase.listCapacities(1, 10, "ASC", "name");

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).listCapacities(1, 10, "ASC", "name");
    }

    @Test
    void testlistCapacities_InvalidPage() {
        Flux<Capacity> result = capacityUseCase.listCapacities(-1, 10, "ASC", "name");

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).listCapacities(anyInt(), anyInt(), anyString(), anyString());
    }

    @Test
    void testlistCapacities_InvalidSize() {
        Flux<Capacity> result = capacityUseCase.listCapacities(1, 0, "ASC", "name");

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).listCapacities(anyInt(), anyInt(), anyString(), anyString());
    }

    @Test
    void testlistCapacities_InvalidField() {
        Flux<Capacity> result = capacityUseCase.listCapacities(1, 10, "ASC", "invalid");

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).listCapacities(anyInt(), anyInt(), anyString(), anyString());
    }

    @Test
    void testlistCapacities_InvalidDirection() {
        Flux<Capacity> result = capacityUseCase.listCapacities(1, 10, "invalid", "name");

        StepVerifier.create(result)
                .expectError(DomainException.class)
                .verify();

        verify(capacityPersistencePort, never()).listCapacities(anyInt(), anyInt(), anyString(), anyString());
    }

    @Test
    void testFindCapacityById() {
        when(capacityPersistencePort.findCapacityByBootcampId(anyLong())).thenReturn(Mono.just(capacity));
        when(technologyPersistencePort.findTechnologiesByCapacity(anyLong())).thenReturn(Flux.just());

        Mono<Capacity> result = capacityUseCase.findCapacityById(1L);

        StepVerifier.create(result)
                .expectNext(capacity)
                .verifyComplete();

        verify(capacityPersistencePort, times(1)).findCapacityByBootcampId(1L);
    }
}
