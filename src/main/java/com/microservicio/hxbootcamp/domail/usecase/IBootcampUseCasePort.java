package com.microservicio.hxbootcamp.domail.usecase;

import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import reactor.core.publisher.Mono;

public interface IBootcampUseCasePort {
    Mono<BootcampModel> guardar(BootcampModel bootcampModel);
}
