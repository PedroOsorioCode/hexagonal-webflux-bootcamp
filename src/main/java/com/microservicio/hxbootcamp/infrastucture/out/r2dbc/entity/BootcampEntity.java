package com.microservicio.hxbootcamp.infrastucture.out.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "bootcamp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampEntity {
    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidadCapacidad;
}
