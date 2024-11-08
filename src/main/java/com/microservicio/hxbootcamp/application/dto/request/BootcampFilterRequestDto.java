package com.microservicio.hxbootcamp.application.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootcampFilterRequestDto {
    private String columnaOrdenamiento;
    private String direccionOrdenamiento;
    private int numeroPagina;
    private int tamanoPorPagina;
}
