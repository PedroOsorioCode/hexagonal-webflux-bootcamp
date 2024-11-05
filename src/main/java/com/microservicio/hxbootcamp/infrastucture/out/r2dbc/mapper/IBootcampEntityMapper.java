package com.microservicio.hxbootcamp.infrastucture.out.r2dbc.mapper;

import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import com.microservicio.hxbootcamp.infrastucture.out.r2dbc.entity.BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IBootcampEntityMapper {
    BootcampEntity toEntityFromModel(BootcampModel bootcampModel);
    BootcampModel toModelFronEntity(BootcampEntity bootcampEntity);
}
