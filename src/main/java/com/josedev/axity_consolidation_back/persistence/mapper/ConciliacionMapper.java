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

    @Mapping(target = "codigoSucursal", source = "sucursalProducto.sucursal.codigoSucursal")
    @Mapping(target = "nombreSucursal", source = "sucursalProducto.sucursal.nombreSucursal")
    @Mapping(target = "codigoProducto", source = "sucursalProducto.producto.codigoProducto")
    @Mapping(target = "nombreProducto", source = "sucursalProducto.producto.nombreProducto")
    @Mapping(target = "codigoDocumento", source = "sucursalProducto.documento.codigoDocumento")
    @Mapping(target = "codigoEstado", source = "estadoConciliacion.codigoEstado")
    @Mapping(target = "descripcionEstado", source = "estadoConciliacion.descripcion")
    @Mapping(target = "sucursal", source = "sucursalProducto.sucursal")
    @Mapping(target = "producto", source = "sucursalProducto.producto")
    @Mapping(target = "documento", source = "sucursalProducto.documento")
    Conciliacion entityToModel(ConciliacionEntity entity);

    @Mapping(target = "sucursalProducto", source = "sucursalProducto")
    @Mapping(target = "estadoConciliacion", source = "estadoConciliacion")
    ConciliacionEntity modelToEntity(Conciliacion model);

    @Mapping(target = "idConciliacion", source = "idConciliacion")
    @Mapping(target = "fechaConciliacion", source = "fechaConciliacion")
    @Mapping(target = "codigoSucursal", source = "codigoSucursal")
    @Mapping(target = "nombreSucursal", source = "nombreSucursal")
    @Mapping(target = "codigoProducto", source = "codigoProducto")
    @Mapping(target = "nombreProducto", source = "nombreProducto")
    @Mapping(target = "codigoDocumento", source = "codigoDocumento")
    @Mapping(target = "diferenciaFisica", source = "diferenciaFisica")
    @Mapping(target = "diferenciaValor", source = "diferenciaValor")
    @Mapping(target = "codigoEstado", source = "codigoEstado")
    @Mapping(target = "descripcionEstado", source = "descripcionEstado")
    ConciliacionDTO modelToDto(Conciliacion model);

    Conciliacion dtoToModel(ConciliacionDTO dto);

    List<Conciliacion> entityListToModelList(List<ConciliacionEntity> entities);

    List<ConciliacionDTO> modelListToDtoList(List<Conciliacion> models);

    ConciliacionFiltro dtoToFiltroModel(ConciliacionFiltroDTO dto);

    @Mapping(target = "year", source = "year")
    @Mapping(target = "month", source = "month")
    @Mapping(target = "totalProcesados", source = "totalProcesados")
    @Mapping(target = "totalDescuadrados", source = "totalDescuadrados")
    @Mapping(target = "fechaProceso", source = "fechaProceso")
    @Mapping(target = "conciliacionesDescuadradas", source = "conciliacionesDescuadradas", qualifiedByName = "conciliacionesToDTOs")
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