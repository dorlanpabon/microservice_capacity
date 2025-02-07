package com.pragma.powerup.infrastructure.entrypoints;

import com.pragma.powerup.infrastructure.entrypoints.BootcampCapacityRestController;
import com.pragma.powerup.infrastructure.entrypoints.handler.IBootcampCapacityHandler;
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

class BootcampCapacityRestControllerTest {
    @Mock
    IBootcampCapacityHandler bootcampCapacityHandler;
    @InjectMocks
    BootcampCapacityRestController bootcampCapacityRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity() {
        when(bootcampCapacityHandler.saveCapacitiesBootcamp(anyLong(), any(List.class))).thenReturn(Mono.empty());

        Mono<Void> result = bootcampCapacityRestController.saveCapacitiesBootcamp(1L, List.of(1L));

        StepVerifier.create(result)
                .verifyComplete();

        verify(bootcampCapacityHandler, times(1)).saveCapacitiesBootcamp(1L, List.of(1L));
    }
}