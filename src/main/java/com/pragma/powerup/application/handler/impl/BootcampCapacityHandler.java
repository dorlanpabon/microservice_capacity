package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.handler.IBootcampCapacityHandler;
import com.pragma.powerup.domain.api.IBootcampCapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BootcampCapacityHandler implements IBootcampCapacityHandler {

    private final IBootcampCapacityServicePort bootcampCapacityServicePort;

    @Override
    public Mono<Void> saveCapacitiesBootcamp(Long bootcampId, List<Long> capacities) {
        return bootcampCapacityServicePort.saveCapacitiesBootcamp(bootcampId, capacities);
    }

}