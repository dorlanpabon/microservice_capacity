package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.CapacityResponseDto;
import com.pragma.powerup.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityResponseMapper {

    CapacityResponseDto toTechnologyResponseDto(Capacity capacity);

}
