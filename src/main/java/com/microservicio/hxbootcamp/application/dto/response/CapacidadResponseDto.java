package com.microservicio.hxbootcamp.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CapacidadResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
}
