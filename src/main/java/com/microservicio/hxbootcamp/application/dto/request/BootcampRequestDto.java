package com.microservicio.hxbootcamp.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampRequestDto {
    private String nombre;
    private String descripcion;
    private int cantidadCapacidad;
}
