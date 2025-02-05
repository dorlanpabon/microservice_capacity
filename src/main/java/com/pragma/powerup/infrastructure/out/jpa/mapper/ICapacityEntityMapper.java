package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Capacity;
import com.pragma.powerup.infrastructure.out.jpa.entity.CapacityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICapacityEntityMapper {

    CapacityEntity toEntity(Capacity tecnology);
    Capacity toTechnology(CapacityEntity tecnologyEntity);
}