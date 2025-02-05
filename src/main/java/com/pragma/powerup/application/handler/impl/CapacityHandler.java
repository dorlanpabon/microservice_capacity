package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.application.dto.request.CapacityRequestDto;
import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import com.pragma.powerup.application.handler.ICapacityHandler;
import com.pragma.powerup.application.mapper.ICapacityRequestMapper;
import com.pragma.powerup.application.mapper.ICapacityResponseMapper;
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

    private final ICapacityServicePort technologyServicePort;
    private final ICapacityRequestMapper technologyRequestMapper;
    private final ICapacityResponseMapper technologyResponseMapper;

    @Override
    public Mono<Void> saveTechnology(CapacityRequestDto capacityRequestDto) {
        return Mono.just(capacityRequestDto)
                .map(technologyRequestMapper::toTechnology)
                .flatMap(technologyServicePort::saveTechnology)
                .then();
    }

    @Override
    public Flux<CapacityResponseDto> listTechnologies(CapacityPageRequestDto capacityPageRequestDto) {
        return technologyServicePort.listTechnologies(capacityPageRequestDto.getPage(), capacityPageRequestDto.getSize(), capacityPageRequestDto.getDirection())
                .map(technologyResponseMapper::toTechnologyResponseDto);
    }


}