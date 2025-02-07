package com.pragma.powerup.infrastructure.output.http.adapter;

import com.pragma.powerup.domain.model.Technology;
import com.pragma.powerup.domain.spi.ITechnologyPersistencePort;
import com.pragma.powerup.infrastructure.output.http.client.TechnologyFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements ITechnologyPersistencePort {

    private final TechnologyFeignClient technologyFeignClient;
    private final HttpServletRequest request;

    @Override
    public Mono<Boolean> saveTechnologiesCapacity(Long capacityId, List<Long> technologies) {
        return technologyFeignClient.saveTechnologiesCapacity(capacityId, technologies)
                .thenReturn(true)
                .onErrorResume(e -> Mono.just(false));
    }

    @Override
    public Flux<Technology> findTechnologiesByCapacity(Long id) {
        return technologyFeignClient.findTechnologiesByCapacity(id);
    }

}
