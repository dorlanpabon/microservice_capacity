package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CapacityRequestDto {

    @Schema(description = "Technology name", example = "Java", required = true)
    @Size(max = 50, message = "Technology name must have a maximum of 50 characters")
    @NotNull(message = "Technology name is required")
    private String name;

    @Schema(description = "Technology description", example = "Java is a programming language", required = true)
    @Size(max = 100, message = "Technology description must have a maximum of 100 characters")
    @NotNull(message = "Technology description is required")
    private String description;

}
