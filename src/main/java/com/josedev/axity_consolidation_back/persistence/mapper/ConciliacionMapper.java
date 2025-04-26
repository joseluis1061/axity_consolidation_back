package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;
import com.josedev.axity_consolidation_back.persistence.entity.ConciliacionEntity;
import com.josedev.axity_consolidation_back.web.dto.ConciliacionDTO;
import com.josedev.axity_consolidation_back.web.dto.ConciliacionFiltroDTO;
import com.josedev.axity_consolidation_back.web.dto.ProcesoBatchResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SucursalProductoMapper.class,
        EstadoConciliacionMapper.class,
        SucursalMapper.class,
        ProductoMapper.class,
        DocumentoMapper.class
})
public interface ConciliacionMapper {

    ConciliacionMapper INSTANCE = Mappers.getMapper(ConciliacionMapper.class);

    @Mapping(target = "codigoSucursal", expression = "java(entity.getSucursalProducto().getSucursal().getCodigoSucursal())")
    @Mapping(target = "nombreSucursal", expression = "java(entity.getSucursalProducto().getSucursal().getNombreSucursal())")
    @Mapping(target = "codigoProducto", expression = "java(entity.getSucursalProducto().getProducto().getCodigoProducto())")
    @Mapping(target = "nombreProducto", expression = "java(entity.getSucursalProducto().getProducto().getNombreProducto())")
    @Mapping(target = "codigoDocumento", expression = "java(entity.getSucursalProducto().getDocumento().getCodigoDocumento())")
    @Mapping(target = "codigoEstado", expression = "java(entity.getEstadoConciliacion().getCodigoEstado())")
    @Mapping(target = "descripcionEstado", expression = "java(entity.getEstadoConciliacion().getDescripcion())")
    @Mapping(target = "sucursal", expression = "java(sucursalMapper.entityToModel(entity.getSucursalProducto().getSucursal()))")
    @Mapping(target = "producto", expression = "java(productoMapper.entityToModel(entity.getSucursalProducto().getProducto()))")
    @Mapping(target = "documento", expression = "java(documentoMapper.entityToModel(entity.getSucursalProducto().getDocumento()))")
    Conciliacion entityToModel(ConciliacionEntity entity);

    @Mapping(target = "sucursalProducto", ignore = true)
    ConciliacionEntity modelToEntity(Conciliacion model);

    ConciliacionDTO modelToDto(Conciliacion model);

    Conciliacion dtoToModel(ConciliacionDTO dto);

    List<Conciliacion> entityListToModelList(List<ConciliacionEntity> entities);

    List<ConciliacionDTO> modelListToDtoList(List<Conciliacion> models);

    ConciliacionFiltro dtoToFiltroModel(ConciliacionFiltroDTO dto);

    ProcesoBatchResponseDTO resultToResponseDto(ProcesoBatchResult result);

    @AfterMapping
    default void afterMapping(@MappingTarget Conciliacion conciliacion, ConciliacionEntity entity) {
        if (entity.getSucursalProducto() != null) {
            if (entity.getSucursalProducto().getSucursal() != null) {
                conciliacion.setCodigoSucursal(entity.getSucursalProducto().getSucursal().getCodigoSucursal());
                conciliacion.setNombreSucursal(entity.getSucursalProducto().getSucursal().getNombreSucursal());
            }
            if (entity.getSucursalProducto().getProducto() != null) {
                conciliacion.setCodigoProducto(entity.getSucursalProducto().getProducto().getCodigoProducto());
                conciliacion.setNombreProducto(entity.getSucursalProducto().getProducto().getNombreProducto());
            }
            if (entity.getSucursalProducto().getDocumento() != null) {
                conciliacion.setCodigoDocumento(entity.getSucursalProducto().getDocumento().getCodigoDocumento());
            }
        }
        if (entity.getEstadoConciliacion() != null) {
            conciliacion.setCodigoEstado(entity.getEstadoConciliacion().getCodigoEstado());
            conciliacion.setDescripcionEstado(entity.getEstadoConciliacion().getDescripcion());
        }
    }
}