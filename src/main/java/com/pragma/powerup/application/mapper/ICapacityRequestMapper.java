package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.CapacityRequestDto;
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
