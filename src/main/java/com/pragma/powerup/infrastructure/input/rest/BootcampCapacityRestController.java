package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.handler.IBootcampCapacityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/bootcamp-capacity")
@RequiredArgsConstructor
public class BootcampCapacityRestController {

    private final IBootcampCapacityHandler bootcampCapacityHandler;

    @Operation(summary = "Save capacities bootcamp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capacities bootcamp saved", content = @Content),
            @ApiResponse(responseCode = "400", description = "Capacities bootcamp not saved", content = @Content)
    })
    @PostMapping("/{bootcampId}/save")
    public Mono<Void> saveCapacitiesBootcamp(@PathVariable Long bootcampId, @RequestBody List<Long> capacities) {
        return bootcampCapacityHandler.saveCapacitiesBootcamp(bootcampId, capacities).then();
    }

}