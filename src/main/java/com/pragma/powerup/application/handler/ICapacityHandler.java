package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.application.dto.request.CapacityRequestDto;
import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapacityHandler {

    Mono<Void> saveCapacity(CapacityRequestDto capacityRequestDto);

    Flux<CapacityResponseDto> listCapacities(CapacityPageRequestDto capacityPageRequestDto);

    Mono<CapacityResponseDto> findCapacityById(Long id);
}