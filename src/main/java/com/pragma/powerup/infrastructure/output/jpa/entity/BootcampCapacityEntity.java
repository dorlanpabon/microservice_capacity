package com.pragma.powerup.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp_capacity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BootcampCapacityEntity {
    @Id
    private Long id;  // Se espera que la base de datos genere el ID de forma automática

    @Column("bootcamp_id")
    private Long bootcampId;

    @Column("capacity_id")
    private Long capacityId;
}
