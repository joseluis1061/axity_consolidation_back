package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.SucursalProductoId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SucursalProductoIdMapper {

    SucursalProductoIdMapper INSTANCE = Mappers.getMapper(SucursalProductoIdMapper.class);

    SucursalProductoId entityToModel(com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoId entity);

    com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoId modelToEntity(SucursalProductoId model);
}