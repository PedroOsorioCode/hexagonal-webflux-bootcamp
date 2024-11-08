package com.microservicio.hxbootcamp.infrastucture.input.rest;

import com.microservicio.hxbootcamp.application.dto.request.BootcampFilterRequestDto;
import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampPaginacionResponseDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import com.microservicio.hxbootcamp.application.service.IBootcampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BootcampController {
    private final IBootcampService bootcampService;

    @Operation(summary = "Validar la salud de la aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salud okey", content = @Content)
    })
    @GetMapping("/health")
    public Mono<String> health(){
        return Mono.just("ok");
    }

    @Operation(summary = "Registrar bootcamp con las capacidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bootcamp guardado correctamente", content = @Content),
            @ApiResponse(responseCode = "409", description = "Inconsistencia en la información", content = @Content)
    })
    @PostMapping
    public Mono<ResponseEntity<BootcampResponseDto>> guardar(@RequestBody Mono<BootcampRequestDto> bootcampRequestDto) {
        return bootcampService.guardar(bootcampRequestDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/listar")
    public Mono<ResponseEntity<BootcampPaginacionResponseDto<BootcampResponseDto>>> consultarTodos(
            @RequestBody Mono<BootcampFilterRequestDto> capacidadFilterRequestDTO) {

        return bootcampService.consultarBootcampTodosPaginado(capacidadFilterRequestDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
