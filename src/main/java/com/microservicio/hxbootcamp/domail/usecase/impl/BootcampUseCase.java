package com.microservicio.hxbootcamp.domail.usecase.impl;

import com.microservicio.hxbootcamp.common.UseCase;
import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import com.microservicio.hxbootcamp.domail.serviceprovider.IBootcampPersistencePort;
import com.microservicio.hxbootcamp.domail.usecase.IBootcampUseCasePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class BootcampUseCase implements IBootcampUseCasePort {
    private final IBootcampPersistencePort bootcampPersistencePort;

    @Override
    public Mono<BootcampModel> guardar(BootcampModel bootcampModel) {
        return bootcampPersistencePort.guardar(bootcampModel);
    }
}
