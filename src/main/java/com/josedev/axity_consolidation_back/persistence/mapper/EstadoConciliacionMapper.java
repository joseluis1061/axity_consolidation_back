package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre EstadoConciliacionEntity y EstadoConciliacion.
 * Utiliza MapStruct para la implementación automática de estos métodos.
 */
@Mapper(componentModel = "spring")
public interface EstadoConciliacionMapper {

    /**
     * Convierte una entidad de estado de conciliación a un modelo de dominio
     * @param estadoConciliacionEntity La entidad de estado de conciliación proveniente de la base de datos
     * @return El modelo de dominio estado de conciliación
     */
    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcion", source = "descripcion")
    EstadoConciliacion toEstadoConciliacion(EstadoConciliacionEntity estadoConciliacionEntity);

    /**
     * Convierte un modelo de dominio estado de conciliación a una entidad de estado de conciliación
     * @param estadoConciliacion El modelo de dominio estado de conciliación
     * @return La entidad de estado de conciliación para persistir en base de datos
     */
    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "conciliaciones", ignore = true)
    EstadoConciliacionEntity toEstadoConciliacionEntity(EstadoConciliacion estadoConciliacion);

    /**
     * Convierte una lista de entidades estado de conciliación a una lista de modelos de dominio
     * @param estadoConciliacionEntities Lista de entidades estado de conciliación
     * @return Lista de modelos de dominio estado de conciliación
     */
    List<EstadoConciliacion> toEstadoConciliacionList(List<EstadoConciliacionEntity> estadoConciliacionEntities);
}

    /*
    EstadoConciliacionMapper INSTANCE = Mappers.getMapper(EstadoConciliacionMapper.class);

    EstadoConciliacion entityToModel(EstadoConciliacionEntity entity);

    EstadoConciliacionEntity modelToEntity(EstadoConciliacion model);

    EstadoConciliacionDTO modelToDto(EstadoConciliacion model);

    EstadoConciliacion dtoToModel(EstadoConciliacionDTO dto);

    List<EstadoConciliacion> entityListToModelList(List<EstadoConciliacionEntity> entities);

    List<EstadoConciliacionDTO> modelListToDtoList(List<EstadoConciliacion> models);

     */
