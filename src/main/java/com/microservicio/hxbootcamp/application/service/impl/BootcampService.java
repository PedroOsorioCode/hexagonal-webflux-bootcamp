package com.microservicio.hxbootcamp.application.service.impl;

import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
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
            .flatMap(data -> bootcampUseCasePort.guardar(data))
                .map(res -> {
                    BootcampResponseDto bootcampResponseDto = bootcampModelMapper.toResponseFromModel(res);
                    bootcampResponseDto.setCantidadCapacidad(res.getCantidadCapacidad());
                    return bootcampResponseDto;
                });
    }
}
