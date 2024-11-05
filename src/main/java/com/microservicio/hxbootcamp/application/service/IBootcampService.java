package com.microservicio.hxbootcamp.application.service;

import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import reactor.core.publisher.Mono;

public interface IBootcampService {
    Mono<BootcampResponseDto> guardar(Mono<BootcampRequestDto> bootcampRequestDto);
}
