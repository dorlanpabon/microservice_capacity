package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.CapacityPageRequestDto;
import com.pragma.powerup.application.dto.request.CapacityRequestDto;
import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import com.pragma.powerup.application.handler.ICapacityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
public class CapacityRestController {

    private final ICapacityHandler capacityHandler;

    @Operation(summary = "Add a new capacity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Capacity created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Capacity already exists", content = @Content)
    })
    @PostMapping("/")
    public Mono<Void> saveCapacity(@Valid @RequestBody CapacityRequestDto capacityRequestDto) {
        return capacityHandler.saveCapacity(capacityRequestDto).then();
    }

    @Operation(summary = "List of technologies paginated by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of technologies", content = @Content)
    })
    @PostMapping("/list")
    public Flux<CapacityResponseDto> listCapacities(@Valid @RequestBody CapacityPageRequestDto capacityPageRequestDto) {
        return capacityHandler.listCapacities(capacityPageRequestDto);
    }

}