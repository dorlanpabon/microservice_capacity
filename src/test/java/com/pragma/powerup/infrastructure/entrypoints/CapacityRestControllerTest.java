package com.pragma.powerup.infrastructure.entrypoints;

import com.pragma.powerup.infrastructure.entrypoints.CapacityRestController;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.CapacityRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.response.CapacityResponseDto;
import com.pragma.powerup.infrastructure.entrypoints.handler.ICapacityHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CapacityRestControllerTest {
    @Mock
    ICapacityHandler capacityHandler;

    @InjectMocks
    CapacityRestController capacityRestController;

    @Mock
    CapacityRequestDto capacityRequestDto;

    @Mock
    CapacityPageRequestDto capacityPageRequestDto;

    @Mock
    CapacityResponseDto capacityResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        capacityRequestDto = new CapacityRequestDto();
        capacityRequestDto.setName("Todo");
        capacityRequestDto.setDescription("Todo");

        capacityPageRequestDto = new CapacityPageRequestDto();
        capacityPageRequestDto.setPage(1);
        capacityPageRequestDto.setSize(10);
        capacityPageRequestDto.setDirection("ASC");

        capacityResponseDto = new CapacityResponseDto();
        capacityResponseDto.setId(1L);
        capacityResponseDto.setName("Todo");
        capacityResponseDto.setDescription("Todo");
    }

    @Test
    void testsaveCapacity() {
        when(capacityHandler.saveCapacity(any(CapacityRequestDto.class))).thenReturn(Mono.empty());

        Mono<Void> result = capacityRestController.saveCapacity(capacityRequestDto);

        assertNotNull(result);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityHandler, times(1)).saveCapacity(any(CapacityRequestDto.class));
    }

    @Test
    void testlistCapacities() {
        when(capacityHandler.listCapacities(any())).thenReturn(Flux.just(capacityResponseDto));

        Flux<CapacityResponseDto>  result = capacityRestController.listCapacities(capacityPageRequestDto);

        assertNotNull(result);

        result.subscribe(response -> assertEquals("Todo", response.getName()));

        verify(capacityHandler, times(1)).listCapacities(any());
    }

    @Test
    void testfindCapacityById() {
        when(capacityHandler.findCapacityById(anyLong())).thenReturn(Mono.just(capacityResponseDto));

        Mono<CapacityResponseDto> result = capacityRestController.findCapacityById(1L);

        assertNotNull(result);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getName().equals("Todo"))
                .verifyComplete();

        verify(capacityHandler, times(1)).findCapacityById(anyLong());
    }
}
