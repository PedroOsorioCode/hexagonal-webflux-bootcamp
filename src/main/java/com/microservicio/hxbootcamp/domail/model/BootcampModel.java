package com.microservicio.hxbootcamp.domail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampModel {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidadCapacidad;
}
