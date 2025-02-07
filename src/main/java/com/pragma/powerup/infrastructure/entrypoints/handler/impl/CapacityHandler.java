package com.pragma.powerup.infrastructure.entrypoints.handler.impl;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.request.CapacityRequestDto;
import com.pragma.powerup.infrastructure.entrypoints.dto.response.CapacityResponseDto;
import com.pragma.powerup.infrastructure.entrypoints.handler.ICapacityHandler;
import com.pragma.powerup.infrastructure.entrypoints.mapper.ICapacityRequestMapper;
import com.pragma.powerup.infrastructure.entrypoints.mapper.ICapacityResponseMapper;
import com.pragma.powerup.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class CapacityHandler implements ICapacityHandler {

    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @Override
    public Mono<Void> saveCapacity(CapacityRequestDto capacityRequestDto) {
        return Mono.just(capacityRequestDto)
                .map(capacityRequestMapper::toCapacity)
                .flatMap(capacityServicePort::saveCapacity)
                .then();
    }

    @Override
    public Flux<CapacityResponseDto> listCapacities(CapacityPageRequestDto capacityPageRequestDto) {
        return capacityServicePort.listCapacities(capacityPageRequestDto.getPage(), capacityPageRequestDto.getSize(), capacityPageRequestDto.getDirection(), capacityPageRequestDto.getField())
                .map(capacityResponseMapper::toCapacityResponseDto);
    }

    @Override
    public Mono<CapacityResponseDto> findCapacityById(Long id) {
        return capacityServicePort.findCapacityById(id)
                .map(capacityResponseMapper::toCapacityResponseDto);
    }


}