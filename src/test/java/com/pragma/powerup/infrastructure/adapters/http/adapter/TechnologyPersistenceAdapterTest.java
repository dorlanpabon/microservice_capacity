package com.pragma.powerup.infrastructure.adapters.http.adapter;

import com.pragma.powerup.infrastructure.adapters.http.client.TechnologyFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.mockito.Mockito.*;

class TechnologyPersistenceAdapterTest {

    @Mock
    TechnologyFeignClient technologyFeignClient;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    TechnologyPersistenceAdapter technologyPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity_Success() {
        when(technologyFeignClient.saveTechnologiesCapacity(anyLong(), any(List.class)))
                .thenReturn(Mono.just(true));

        Mono<Boolean> result = technologyPersistenceAdapter.saveTechnologiesCapacity(1L, List.of(1L));

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        verify(technologyFeignClient, times(1)).saveTechnologiesCapacity(1L, List.of(1L));
    }

    @Test
    void testSaveTechnologiesCapacity_ErrorHandling() {
        when(technologyFeignClient.saveTechnologiesCapacity(anyLong(), any(List.class)))
                .thenReturn(Mono.error(new RuntimeException("Error en Feign")));

        Mono<Boolean> result = technologyPersistenceAdapter.saveTechnologiesCapacity(1L, List.of(1L));

        StepVerifier.create(result)
                .expectNext(false)
                .verifyComplete();

        verify(technologyFeignClient, times(1)).saveTechnologiesCapacity(1L, List.of(1L));
    }

    @Test

    void testFindTechnologiesByCapacity() {
        when(technologyFeignClient.findTechnologiesByCapacity(anyLong()))
                .thenReturn(Flux.empty());

        technologyPersistenceAdapter.findTechnologiesByCapacity(1L);

        verify(technologyFeignClient, times(1)).findTechnologiesByCapacity(1L);
    }
}
