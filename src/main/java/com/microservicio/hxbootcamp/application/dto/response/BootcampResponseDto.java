package com.microservicio.hxbootcamp.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BootcampResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidadCapacidad;
}
