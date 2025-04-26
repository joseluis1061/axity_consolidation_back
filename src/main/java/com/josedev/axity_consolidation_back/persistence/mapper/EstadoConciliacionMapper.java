package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import com.josedev.axity_consolidation_back.web.dto.EstadoConciliacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoConciliacionMapper {

    EstadoConciliacionMapper INSTANCE = Mappers.getMapper(EstadoConciliacionMapper.class);

    @Mapping(target = "conciliaciones", source = "conciliaciones")
    EstadoConciliacion entityToModel(EstadoConciliacionEntity entity);

    @Mapping(target = "conciliaciones", source = "conciliaciones")
    EstadoConciliacionEntity modelToEntity(EstadoConciliacion model);

    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcion", source = "descripcion")
    EstadoConciliacionDTO modelToDto(EstadoConciliacion model);

    EstadoConciliacion dtoToModel(EstadoConciliacionDTO dto);

    List<EstadoConciliacion> entityListToModelList(List<EstadoConciliacionEntity> entities);

    List<EstadoConciliacionDTO> modelListToDtoList(List<EstadoConciliacion> models);
}