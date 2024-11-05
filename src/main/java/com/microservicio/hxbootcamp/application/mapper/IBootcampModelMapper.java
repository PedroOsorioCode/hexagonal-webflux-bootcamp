package com.microservicio.hxbootcamp.application.mapper;

import com.microservicio.hxbootcamp.application.dto.request.BootcampRequestDto;
import com.microservicio.hxbootcamp.application.dto.response.BootcampResponseDto;
import com.microservicio.hxbootcamp.domail.model.BootcampModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampModelMapper {
    BootcampModel toModelFromRequest(BootcampRequestDto capacidadRequestDto);
    BootcampResponseDto toResponseFromModel(BootcampModel capacidadModel);
}
