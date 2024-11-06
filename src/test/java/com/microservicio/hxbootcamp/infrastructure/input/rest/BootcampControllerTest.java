package com.microservicio.hxbootcamp.infrastructure.input.rest;

import com.microservicio.hxbootcamp.application.dto.request.BootcampFilterRequestDto;
import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampPaginacionResponseDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import com.microservicio.hxbootcamp.application.service.impl.BootcampService;
import com.microservicio.hxbootcamp.infrastucture.input.rest.BootcampController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebFluxTest(controllers = BootcampController.class)
public class BootcampControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private BootcampService bootcampService;

    private String nombre = "test";
    private String descripcion = "testd";

    @Test
    void testHealth() {
        webTestClient.get()
                .uri("/api/bootcamp/health")  // Cambia la URI si es diferente
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("ok");
    }

    @Test
    void testGuardarExitoso() {
        // Preparar datos de prueba
        BootcampRequestDto requestDto = BootcampRequestDto
                .builder().nombre(this.nombre).descripcion(this.descripcion).build();

        BootcampResponseDto responseDto = BootcampResponseDto
                .builder().nombre(this.nombre).descripcion(this.descripcion).build();

        // Mockear el comportamiento del servicio para que devuelva una respuesta exitosa
        when(bootcampService.guardar(any(Mono.class))).thenReturn(Mono.just(responseDto));

        // Ejecutar la prueba
        webTestClient.post()
                .uri("/api/bootcamp") // Cambia el URI si es diferente
                .contentType(APPLICATION_JSON)
                .body(Mono.just(requestDto), BootcampRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testListarExitoso() {
        BootcampFilterRequestDto filterDto = new BootcampFilterRequestDto(); // Configura el filtro según sea necesario
        BootcampResponseDto responseDto = new BootcampResponseDto(); // Configura la respuesta según sea necesario
        BootcampPaginacionResponseDto<BootcampResponseDto> paginacionResponseDto = new BootcampPaginacionResponseDto<>();
        paginacionResponseDto.setContent(List.of(responseDto)); // Agrega elementos a la paginación

        when(bootcampService.consultarBootcampTodosPaginado(any())).thenReturn(Mono.just(paginacionResponseDto));

        webTestClient.post()
                .uri("/api/bootcamp/listar") // Cambia el URI si es diferente
                .contentType(APPLICATION_JSON)
                .body(Mono.just(filterDto), BootcampFilterRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }

}
