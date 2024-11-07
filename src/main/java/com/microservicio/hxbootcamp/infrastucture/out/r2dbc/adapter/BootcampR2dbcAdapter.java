package com.microservicio.hxbootcamp.infrastucture.out.r2dbc.adapter;

import com.microservicio.hxbootcamp.common.PersistenceAdapter;
import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import com.microservicio.hxbootcamp.domail.serviceprovider.IBootcampPersistencePort;
import com.microservicio.hxbootcamp.infrastucture.out.r2dbc.mapper.IBootcampEntityMapper;
import com.microservicio.hxbootcamp.infrastucture.out.r2dbc.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class BootcampR2dbcAdapter implements IBootcampPersistencePort {
    private final BootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    @Override
    public Mono<BootcampModel> guardar(BootcampModel bootcampModel) {
        return bootcampRepository.save(bootcampEntityMapper.toEntityFromModel(bootcampModel))
                .map(bootcampEntityMapper::toModelFronEntity);
    }

    @Override
    public Flux<BootcampModel> obtenerTodos() {
        return bootcampRepository.findAll()
                .map(bootcampEntityMapper::toModelFronEntity);
    }
}
