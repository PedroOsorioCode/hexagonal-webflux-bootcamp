package com.microservicio.hxbootcamp.domail.serviceprovider;

import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampPersistencePort {
    Mono<BootcampModel> guardar(BootcampModel bootcampModel);
    Flux<BootcampModel> obtenerTodos();
}
