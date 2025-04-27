package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;
import com.josedev.axity_consolidation_back.persistence.entity.ConciliacionEntity;
import com.josedev.axity_consolidation_back.web.dto.ConciliacionDTO;
import com.josedev.axity_consolidation_back.web.dto.ConciliacionFiltroDTO;
import com.josedev.axity_consolidation_back.web.dto.ProcesoBatchResponseDTO;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SucursalProductoMapper.class,
        EstadoConciliacionMapper.class,
        SucursalMapper.class,
        ProductoMapper.class,
        DocumentoMapper.class
})
public interface ConciliacionMapper {

    Conciliacion entityToModel(ConciliacionEntity entity);

    ConciliacionEntity modelToEntity(Conciliacion model);

    ConciliacionDTO modelToDto(Conciliacion model);

    Conciliacion dtoToModel(ConciliacionDTO dto);

    List<Conciliacion> entityListToModelList(List<ConciliacionEntity> entities);

    List<ConciliacionDTO> modelListToDtoList(List<Conciliacion> models);

    ConciliacionFiltro dtoToFiltroModel(ConciliacionFiltroDTO dto);

    ProcesoBatchResponseDTO resultToResponseDto(ProcesoBatchResult result);

    @Named("conciliacionesToDTOs")
    default List<ConciliacionDTO> mapConciliacionesToDTOs(List<Conciliacion> conciliaciones) {
        return modelListToDtoList(conciliaciones);
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Conciliacion conciliacion, ConciliacionEntity entity) {
        // Este método se mantiene, pero ya no es necesario configurar propiedades manualmente
        // porque los mapeos ya están correctamente definidos en las anotaciones @Mapping
    }

    // Método para mapear un Page de entidades a un Page de DTOs
    default Page<ConciliacionDTO> entityPageToDtoPage(Page<ConciliacionEntity> entityPage) {
        List<Conciliacion> models = entityListToModelList(entityPage.getContent());
        List<ConciliacionDTO> dtos = modelListToDtoList(models);
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
    }

    // Método para mapear un Page de entidades a un Page de modelos
    default Page<Conciliacion> entityPageToModelPage(Page<ConciliacionEntity> entityPage) {
        List<Conciliacion> models = entityListToModelList(entityPage.getContent());
        return new PageImpl<>(models, entityPage.getPageable(), entityPage.getTotalElements());
    }
}