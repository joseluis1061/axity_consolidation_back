package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalEntity;
import com.josedev.axity_consolidation_back.web.dto.SucursalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SucursalMapper {

    SucursalMapper INSTANCE = Mappers.getMapper(SucursalMapper.class);

    @Mapping(target = "sucursalProductos", source = "sucursalProductos")
    Sucursal entityToModel(SucursalEntity entity);

    @Mapping(target = "sucursalProductos", source = "sucursalProductos")
    SucursalEntity modelToEntity(Sucursal model);

    @Mapping(target = "codigoSucursal", source = "codigoSucursal")
    @Mapping(target = "nombreSucursal", source = "nombreSucursal")
    SucursalDTO modelToDto(Sucursal model);

    Sucursal dtoToModel(SucursalDTO dto);

    List<Sucursal> entityListToModelList(List<SucursalEntity> entities);

    List<SucursalDTO> modelListToDtoList(List<Sucursal> models);
}