package com.pragma.powerup.infrastructure.output.http.client;

import com.pragma.powerup.domain.model.Technology;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@ReactiveFeignClient(name = "technology-service", url = "http://localhost:8081")
public interface TechnologyFeignClient {

    @PostMapping("capacity-technology/{capacityId}/save")
    Mono<Void> saveTechnologiesCapacity(@PathVariable Long capacityId, @RequestBody List<Long> technologies);

    @GetMapping("technology/{id}")
    Flux<Technology> findTechnologiesByCapacity(@PathVariable Long id);
}
