package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.Technology;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CapacityResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer technologyCount;
    private List<Technology> technologyList;
}
