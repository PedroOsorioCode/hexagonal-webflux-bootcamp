package com.microservicio.hxbootcamp.application.service;

import com.microservicio.hxbootcamp.application.common.MensajeError;
import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import com.microservicio.hxbootcamp.application.mapper.IBootcampModelMapper;
import com.microservicio.hxbootcamp.application.service.impl.BootcampService;
import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import com.microservicio.hxbootcamp.domail.usecase.IBootcampUseCasePort;
import com.microservicio.hxbootcamp.domail.usecase.impl.BootcampUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BootcampServiceTest {
    @InjectMocks
    private BootcampService bootcampService;
    @Mock
    BootcampUseCase bootcampUseCase;
    @Mock
    private IBootcampModelMapper bootcampModelMapper;

    private String nombre = "test";
    private String descripcion = "testd";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testValidarGuardarExitoso() {
        BootcampRequestDto requestDto = BootcampRequestDto
                .builder().nombre(this.nombre).descripcion(this.descripcion)
                .cantidadCapacidad(3).build();

        when(bootcampModelMapper.toModelFromRequest(any())).thenReturn(new BootcampModel());
        when(bootcampModelMapper.toResponseFromModel(any())).thenReturn(new BootcampResponseDto());
        when(bootcampUseCase.guardar(any())).thenReturn(Mono.just(new BootcampModel(1L, "a", "b", 3)));

        Mono<BootcampRequestDto> requestMono = Mono.just(requestDto);
        Mono<BootcampResponseDto> responseMono = bootcampService.guardar(requestMono);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response instanceof BootcampResponseDto)
                .verifyComplete();
    }
}
