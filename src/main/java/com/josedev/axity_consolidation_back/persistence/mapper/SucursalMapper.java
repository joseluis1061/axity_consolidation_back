package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SucursalMapper {

    // Entity to Domain
    Sucursal toSucursal(SucursalEntity entity);
    List<Sucursal> toSucursales(List<SucursalEntity> entities);

    // Domain to Entity
    SucursalEntity toSucursalEntity(Sucursal sucursal);

}

/*
    SucursalMapper INSTANCE = Mappers.getMapper(SucursalMapper.class);

    Sucursal entityToModel(SucursalEntity entity);

    SucursalEntity modelToEntity(Sucursal model);

    SucursalDTO modelToDto(Sucursal model);

    Sucursal dtoToModel(SucursalDTO dto);

    List<Sucursal> entityListToModelList(List<SucursalEntity> entities);

    List<SucursalDTO> modelListToDtoList(List<Sucursal> models);
 */