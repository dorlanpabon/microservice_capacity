package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.application.dto.request.CapacityRequestDto;
import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import com.pragma.powerup.application.mapper.ICapacityRequestMapper;
import com.pragma.powerup.application.mapper.ICapacityResponseMapper;
import com.pragma.powerup.domain.api.ICapacityServicePort;
import com.pragma.powerup.domain.model.Capacity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class CapacityHandlerTest {
    @Mock
    ICapacityServicePort capacityServicePort;
    @Mock
    ICapacityRequestMapper capacityRequestMapper;
    @Mock
    ICapacityResponseMapper capacityResponseMapper;
    @InjectMocks
    CapacityHandler capacityHandler;
    @Mock
    CapacityRequestDto capacityRequestDto;
    @Mock
    CapacityResponseDto capacityResponseDto;
    @Mock
    CapacityPageRequestDto capacityPageRequestDto;
    @Mock
    Capacity capacity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityRequestDto = new CapacityRequestDto();
        capacityRequestDto.setName("name");
        capacityRequestDto.setDescription("description");

        capacity = new Capacity();
        capacity.setId(1L);
        capacity.setName("name");
        capacity.setDescription("description");

        capacityResponseDto = new CapacityResponseDto();
        capacityResponseDto.setId(1L);
        capacityResponseDto.setName("name");
        capacityResponseDto.setDescription("description");

        capacityPageRequestDto = new CapacityPageRequestDto();
        capacityPageRequestDto.setPage(1);
        capacityPageRequestDto.setSize(10);
        capacityPageRequestDto.setDirection("ASC");
    }

    @Test
    void testsaveCapacity() {
        when(capacityRequestMapper.toCapacity(capacityRequestDto)).thenReturn(capacity);
        when(capacityServicePort.saveCapacity(any())).thenReturn(Mono.empty());

        Mono<Void> result = capacityHandler.saveCapacity(capacityRequestDto);

        StepVerifier.create(result)
                .verifyComplete();

        verify(capacityRequestMapper, times(1)).toCapacity(capacityRequestDto);
        verify(capacityServicePort, times(1)).saveCapacity(any());
    }

    @Test
    void testlistCapacities() {
        when(capacityServicePort.listCapacities(1, 10, "ASC")).thenReturn(Flux.just(capacity));
        when(capacityResponseMapper.toCapacityResponseDto(capacity)).thenReturn(capacityResponseDto);

        Flux<CapacityResponseDto> result = capacityHandler.listCapacities(capacityPageRequestDto);

        StepVerifier.create(result)
                .expectNext(capacityResponseDto)
                .verifyComplete();

        verify(capacityServicePort, times(1)).listCapacities(1, 10, "ASC");
        verify(capacityResponseMapper, times(1)).toCapacityResponseDto(capacity);
    }
}