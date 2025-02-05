package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.application.dto.request.CapacityRequestDto;
import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import com.pragma.powerup.application.handler.ICapacityHandler;
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
    ICapacityHandler technologyHandler;

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
    void testSaveTechnology() {
        when(technologyHandler.saveTechnology(any(CapacityRequestDto.class))).thenReturn(Mono.empty());

        Mono<Void> result = capacityRestController.saveTechnology(capacityRequestDto);

        assertNotNull(result);

        StepVerifier.create(result)
                .verifyComplete();

        verify(technologyHandler, times(1)).saveTechnology(any(CapacityRequestDto.class));
    }

    @Test
    void testListTechnologies() {
        when(technologyHandler.listTechnologies(any())).thenReturn(Flux.just(capacityResponseDto));

        Flux<CapacityResponseDto>  result = capacityRestController.listTechnologies(capacityPageRequestDto);

        assertNotNull(result);

        result.subscribe(response -> assertEquals("Todo", response.getName()));

        verify(technologyHandler, times(1)).listTechnologies(any());
    }
}
