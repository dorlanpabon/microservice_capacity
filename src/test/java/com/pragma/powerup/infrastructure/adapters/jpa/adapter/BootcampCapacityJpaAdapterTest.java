package com.pragma.powerup.infrastructure.adapters.jpa.adapter;

import com.pragma.powerup.infrastructure.adapters.jpa.entity.BootcampCapacityEntity;
import com.pragma.powerup.infrastructure.adapters.jpa.mapper.IBootcampCapacityEntityMapper;
import com.pragma.powerup.infrastructure.adapters.jpa.repository.IBootcampCapacityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BootcampCapacityJpaAdapterTest {
    @Mock
    IBootcampCapacityRepository bootcampCapacityRepository;
    @Mock
    IBootcampCapacityEntityMapper bootcampCapacityEntityMapper;
    @InjectMocks
    BootcampCapacityJpaAdapter bootcampCapacityJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTechnologiesCapacity() {
        when(bootcampCapacityEntityMapper.toEntity(anyLong(), anyLong())).thenReturn(new BootcampCapacityEntity(1L, 1L, 1L));
        when(bootcampCapacityRepository.save(any(BootcampCapacityEntity.class))).thenReturn(Mono.empty());

        Mono<Void> result = bootcampCapacityJpaAdapter.saveCapacitiesBootcamp(1L, List.of(1L));

        StepVerifier.create(result)
                .verifyComplete();

        verify(bootcampCapacityEntityMapper, times(1)).toEntity(1L, 1L);
    }
}

