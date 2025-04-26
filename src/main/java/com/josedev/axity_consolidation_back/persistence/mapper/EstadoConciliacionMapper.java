package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import com.josedev.axity_consolidation_back.web.dto.EstadoConciliacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoConciliacionMapper {

    EstadoConciliacionMapper INSTANCE = Mappers.getMapper(EstadoConciliacionMapper.class);

    EstadoConciliacion entityToModel(EstadoConciliacionEntity entity);

    EstadoConciliacionEntity modelToEntity(EstadoConciliacion model);

    EstadoConciliacionDTO modelToDto(EstadoConciliacion model);

    EstadoConciliacion dtoToModel(EstadoConciliacionDTO dto);

    List<EstadoConciliacion> entityListToModelList(List<EstadoConciliacionEntity> entities);

    List<EstadoConciliacionDTO> modelListToDtoList(List<EstadoConciliacion> models);
}