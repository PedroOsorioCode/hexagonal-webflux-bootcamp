package com.microservicio.hxbootcamp.infrastucture.out.r2dbc.repository;

import com.microservicio.hxbootcamp.infrastucture.out.r2dbc.entity.BootcampEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BootcampRepository extends R2dbcRepository<BootcampEntity, Long> {
}
