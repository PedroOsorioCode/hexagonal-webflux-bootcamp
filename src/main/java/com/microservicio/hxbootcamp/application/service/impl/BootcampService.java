package com.microservicio.hxbootcamp.application.service.impl;

import com.microservicio.hxbootcamp.application.common.ConstantesAplicacion;
import com.microservicio.hxbootcamp.application.dto.request.BootcampFilterRequestDto;
import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampPaginacionResponseDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import com.microservicio.hxbootcamp.application.mapper.IBootcampModelMapper;
import com.microservicio.hxbootcamp.application.service.IBootcampService;
import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import com.microservicio.hxbootcamp.domail.usecase.IBootcampUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BootcampService implements IBootcampService {
    private final IBootcampUseCasePort bootcampUseCasePort;
    private final IBootcampModelMapper bootcampModelMapper;

    @Override
    public Mono<BootcampResponseDto> guardar(Mono<BootcampRequestDto> bootcampRequestDto) {
        return bootcampRequestDto
            .flatMap(req -> {
                BootcampModel bootcampModel = bootcampModelMapper.toModelFromRequest(req);
                bootcampModel.setCantidadCapacidad(req.getCantidadCapacidad());
                return Mono.just(bootcampModel);
            })
            .flatMap(bootcampUseCasePort::guardar)
                .map(res -> {
                    BootcampResponseDto bootcampResponseDto = bootcampModelMapper.toResponseFromModel(res);
                    bootcampResponseDto.setCantidadCapacidad(res.getCantidadCapacidad());
                    return bootcampResponseDto;
                });
    }

    @Override
    public Mono<BootcampPaginacionResponseDto<BootcampResponseDto>>
        consultarBootcampTodosPaginado(Mono<BootcampFilterRequestDto> bootcampFilterRequestDTO) {
        return bootcampFilterRequestDTO.flatMap(filter -> bootcampUseCasePort.obtenerTodos()
            .switchIfEmpty(Mono.empty())
            .map(bootcamp ->
                    new BootcampResponseDto(bootcamp.getId(), bootcamp.getNombre(), bootcamp.getDescripcion(), bootcamp.getCantidadCapacidad()))
            .sort((getComparator(filter)))
            .collectList()
            .flatMap(listaBootcamp -> {
                // Calcular la paginación
                int skip = filter.getNumeroPagina() * filter.getTamanoPorPagina();
                List<BootcampResponseDto> paginaBootcamp = listaBootcamp.stream()
                        .skip(skip)
                        .limit(filter.getTamanoPorPagina())
                        .toList();

                return Mono.just(new BootcampPaginacionResponseDto<>(
                        paginaBootcamp,
                        filter.getNumeroPagina(),
                        filter.getTamanoPorPagina(),
                        listaBootcamp.size()));
            }));
    }

    private Comparator<BootcampResponseDto> getComparator(BootcampFilterRequestDto filter) {
        if (ConstantesAplicacion.COLUMN_NOMBRE.equalsIgnoreCase(filter.getColumnaOrdenamiento())) {
            return filter.getDireccionOrdenamiento().equalsIgnoreCase(ConstantesAplicacion.METODO_ORDENAMIENTO_ASC)
                    ? Comparator.comparing(BootcampResponseDto::getNombre)
                    : Comparator.comparing(BootcampResponseDto::getNombre).reversed();
        } else if (ConstantesAplicacion.COLUMN_CANTIDAD.equalsIgnoreCase(filter.getColumnaOrdenamiento())) {
            return filter.getDireccionOrdenamiento().equalsIgnoreCase(ConstantesAplicacion.METODO_ORDENAMIENTO_ASC)
                    ? Comparator.comparingInt(BootcampResponseDto::getCantidadCapacidad)
                    : Comparator.comparingInt(BootcampResponseDto::getCantidadCapacidad).reversed();
        }

        // Comparator por defecto en caso de que el campo no coincida
        return Comparator.comparing(BootcampResponseDto::getNombre);
    }
}
