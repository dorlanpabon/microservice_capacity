package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.domain.api.IBootcampCapacityServicePort;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BootcampCapacityHandlerTest {
    @Mock
    IBootcampCapacityServicePort bootcampCapacityServicePort;
    @InjectMocks
    BootcampCapacityHandler bootcampCapacityHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity() {
        when(bootcampCapacityServicePort.saveCapacitiesBootcamp(anyLong(), any(List.class))).thenReturn(Mono.empty());

        Mono<Void> result = bootcampCapacityHandler.saveCapacitiesBootcamp(1L, List.of(1L));

        StepVerifier.create(result)
                .verifyComplete();

        verify(bootcampCapacityServicePort, times(1)).saveCapacitiesBootcamp(1L, List.of(1L));
    }
}