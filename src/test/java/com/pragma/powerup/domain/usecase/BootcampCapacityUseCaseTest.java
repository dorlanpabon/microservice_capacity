package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.constants.DomainConstants;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.spi.IBootcampCapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BootcampCapacityUseCaseTest {
    @Mock
    private IBootcampCapacityPersistencePort bootcampCapacityPersistencePort;

    @InjectMocks
    private BootcampCapacityUseCase bootcampCapacityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity_Success() {
        Long capacityId = 1L;
        List<Long> technologies = List.of(1L, 2L, 3L);

        when(bootcampCapacityPersistencePort.saveCapacitiesBootcamp(capacityId, technologies)).thenReturn(Mono.empty());

        Mono<Void> result = bootcampCapacityUseCase.saveCapacitiesBootcamp(capacityId, technologies);

        StepVerifier.create(result)
                .verifyComplete();

        verify(bootcampCapacityPersistencePort, times(1)).saveCapacitiesBootcamp(capacityId, technologies);
    }

    @Test
    void testSaveTechnologiesCapacity_InvalidCapacityId_ShouldThrowException() {
        Mono<Void> result = bootcampCapacityUseCase.saveCapacitiesBootcamp(null, List.of(1L));

        StepVerifier.create(result)
                .expectErrorSatisfies(throwable -> {
                    assert throwable instanceof DomainException;
                    assert ((DomainException) throwable).getMessage().equals(DomainConstants.INVALID_BOOTCAMP_ID);
                })
                .verify();

        verify(bootcampCapacityPersistencePort, never()).saveCapacitiesBootcamp(anyLong(), any());
    }

    @Test
    void testSaveTechnologiesCapacity_EmptyTechnologyList_ShouldThrowException() {
        Long capacityId = 1L;
        List<Long> emptyList = List.of();

        Mono<Void> result = bootcampCapacityUseCase.saveCapacitiesBootcamp(capacityId, emptyList);

        StepVerifier.create(result)
                .expectErrorSatisfies(throwable -> {
                    assert throwable instanceof DomainException;
                    assert ((DomainException) throwable).getMessage().equals(DomainConstants.EMPTY_CAPACITY_LIST);
                })
                .verify();

        verify(bootcampCapacityPersistencePort, never()).saveCapacitiesBootcamp(anyLong(), any());
    }

}
