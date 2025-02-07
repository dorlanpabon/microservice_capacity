package com.pragma.powerup.infrastructure.entrypoints.mapper;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.CapacityRequestDto;
import com.pragma.powerup.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityRequestMapper {

    @Mapping(target = "technologyCount", expression = "java(capacityRequestDto.getTechnologies().size())")
    Capacity toCapacity(CapacityRequestDto capacityRequestDto);

}
