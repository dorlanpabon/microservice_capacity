package com.pragma.powerup.infrastructure.output.http.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@ReactiveFeignClient(name = "technology-service", url = "http://localhost:8081")
public interface TechnologyFeignClient {

    @PostMapping("capacity-technology/{capacityId}/save")
    Mono<Void> saveTechnologiesCapacity(@PathVariable Long capacityId, @RequestBody List<Long> technologies);

}
