package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.SucursalProducto;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SucursalMapper.class,
        ProductoMapper.class,
        DocumentoMapper.class,
        SucursalProductoIdMapper.class
})
public interface SucursalProductoMapper {

    SucursalProductoMapper INSTANCE = Mappers.getMapper(SucursalProductoMapper.class);

    SucursalProducto entityToModel(SucursalProductoEntity entity);

    SucursalProductoEntity modelToEntity(SucursalProducto model);

    List<SucursalProducto> entityListToModelList(List<SucursalProductoEntity> entities);
}