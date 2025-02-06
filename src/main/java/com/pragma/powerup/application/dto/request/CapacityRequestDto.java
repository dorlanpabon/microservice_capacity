package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CapacityRequestDto {

    @Schema(description = "Capacity name", example = "Desarrollador backend", required = true)
    @Size(max = 50, message = "Capacity name must have a maximum of 50 characters")
    @NotNull(message = "Capacity name is required")
    private String name;

    @Schema(description = "Capacity description", example = "Desarrollador backend con experiencia en Java", required = true)
    @Size(max = 100, message = "Capacity description must have a maximum of 100 characters")
    @NotNull(message = "Capacity description is required")
    private String description;

    @Schema(description = "Lista de IDs de tecnologías, minimo 3 y máximo 20 elementos", example = "[1, 2, 3]", required = true)
    @Size(min=3, max = 20, message = "La lista de tecnologías debe contener como minimo 3 y máximo 20 elementos")
    @UniqueElements(message = "La lista de tecnologías no puede contener elementos duplicados")
    private List<Long> technologies;

}
