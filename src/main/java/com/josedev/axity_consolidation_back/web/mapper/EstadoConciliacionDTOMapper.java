package com.josedev.axity_consolidation_back.web.mapper;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.web.dto.EstadoConciliacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre EstadoConciliacion y EstadoConciliacionDTO.
 * Utiliza MapStruct para la implementación automática de estos métodos.
 */
@Mapper(componentModel = "spring")
public interface EstadoConciliacionDTOMapper {

    /**
     * Convierte un modelo de dominio a un DTO
     * @param estadoConciliacion El modelo de dominio
     * @return El DTO para la capa web
     */
    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcion", source = "descripcion")
    EstadoConciliacionDTO toEstadoConciliacionDTO(EstadoConciliacion estadoConciliacion);

    /**
     * Convierte un DTO a un modelo de dominio
     * @param estadoConciliacionDTO El DTO de la capa web
     * @return El modelo de dominio
     */
    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcion", source = "descripcion")
    EstadoConciliacion toEstadoConciliacion(EstadoConciliacionDTO estadoConciliacionDTO);

    /**
     * Convierte una lista de modelos de dominio a una lista de DTOs
     * @param estadoConciliaciones Lista de modelos de dominio
     * @return Lista de DTOs
     */
    List<EstadoConciliacionDTO> toEstadoConciliacionDTOList(List<EstadoConciliacion> estadoConciliaciones);
}