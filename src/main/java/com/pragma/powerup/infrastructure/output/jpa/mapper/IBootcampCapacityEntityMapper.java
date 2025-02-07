package com.pragma.powerup.infrastructure.output.jpa.mapper;

import com.pragma.powerup.domain.model.BootcampCapacity;
import com.pragma.powerup.infrastructure.output.jpa.entity.BootcampCapacityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IBootcampCapacityEntityMapper {

    @Mapping(target = "bootcampId", expression = "java(bootcampId)")
    @Mapping(target = "capacityId", expression = "java(capacityId)")
    BootcampCapacityEntity toEntity(Long bootcampId, Long capacityId);

    BootcampCapacityEntity toEntity(BootcampCapacity bootcampCapacity);

    BootcampCapacity toDomain(BootcampCapacityEntity bootcampCapacityEntity);
}