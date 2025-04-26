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
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        SucursalProductoMapper.class,
        EstadoConciliacionMapper.class,
        SucursalMapper.class,
        ProductoMapper.class,
        DocumentoMapper.class
})
public interface ConciliacionMapper {

    // Eliminamos la instancia estática ya que usamos inyección de dependencias
    // ConciliacionMapper INSTANCE = Mappers.getMapper(ConciliacionMapper.class);

    // Simplificamos las anotaciones, dejando la lógica compleja para el afterMapping
    @Mapping(target = "codigoSucursal", ignore = true)
    @Mapping(target = "nombreSucursal", ignore = true)
    @Mapping(target = "codigoProducto", ignore = true)
    @Mapping(target = "nombreProducto", ignore = true)
    @Mapping(target = "codigoDocumento", ignore = true)
    @Mapping(target = "codigoEstado", ignore = true)
    @Mapping(target = "descripcionEstado", ignore = true)
    @Mapping(target = "sucursal", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "documento", ignore = true)
    Conciliacion entityToModel(ConciliacionEntity entity);

    @Mapping(target = "sucursalProducto", ignore = true)
    @Mapping(target = "estadoConciliacion", ignore = true)
    ConciliacionEntity modelToEntity(Conciliacion model);

    ConciliacionDTO modelToDto(Conciliacion model);

    Conciliacion dtoToModel(ConciliacionDTO dto);

    List<Conciliacion> entityListToModelList(List<ConciliacionEntity> entities);

    List<ConciliacionDTO> modelListToDtoList(List<Conciliacion> models);

    ConciliacionFiltro dtoToFiltroModel(ConciliacionFiltroDTO dto);

    ProcesoBatchResponseDTO resultToResponseDto(ProcesoBatchResult result);

    @AfterMapping
    default void afterMapping(@MappingTarget Conciliacion conciliacion, ConciliacionEntity entity) {
        // Manejo seguro de nulos para SucursalProducto
        if (entity.getSucursalProducto() != null) {
            // Sucursal
            if (entity.getSucursalProducto().getSucursal() != null) {
                conciliacion.setCodigoSucursal(entity.getSucursalProducto().getSucursal().getCodigoSucursal());
                conciliacion.setNombreSucursal(entity.getSucursalProducto().getSucursal().getNombreSucursal());
                conciliacion.setSucursal(SucursalMapper.INSTANCE.entityToModel(entity.getSucursalProducto().getSucursal()));
            }

            // Producto
            if (entity.getSucursalProducto().getProducto() != null) {
                conciliacion.setCodigoProducto(entity.getSucursalProducto().getProducto().getCodigoProducto());
                conciliacion.setNombreProducto(entity.getSucursalProducto().getProducto().getNombreProducto());
                conciliacion.setProducto(ProductoMapper.INSTANCE.entityToModel(entity.getSucursalProducto().getProducto()));
            }

            // Documento
            if (entity.getSucursalProducto().getDocumento() != null) {
                conciliacion.setCodigoDocumento(entity.getSucursalProducto().getDocumento().getCodigoDocumento());
                conciliacion.setDocumento(DocumentoMapper.INSTANCE.entityToModel(entity.getSucursalProducto().getDocumento()));
            }
        }

        // Estado de conciliación
        if (entity.getEstadoConciliacion() != null) {
            conciliacion.setCodigoEstado(entity.getEstadoConciliacion().getCodigoEstado());
            conciliacion.setDescripcionEstado(entity.getEstadoConciliacion().getDescripcion());
        }
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

    // Método para configurar la entidad a partir del modelo al guardar
    @AfterMapping
    default void afterModelToEntityMapping(@MappingTarget ConciliacionEntity entity, Conciliacion model) {
        // Aquí puedes implementar lógica adicional si es necesaria
    }
}