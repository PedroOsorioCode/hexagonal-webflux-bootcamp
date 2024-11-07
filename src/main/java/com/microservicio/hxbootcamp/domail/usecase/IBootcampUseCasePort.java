package com.microservicio.hxbootcamp.domail.usecase;

import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampUseCasePort {
    Mono<BootcampModel> guardar(BootcampModel bootcampModel);
    Flux<BootcampModel> obtenerTodos();
}
