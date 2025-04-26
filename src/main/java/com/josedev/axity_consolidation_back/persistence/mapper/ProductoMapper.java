package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Producto;
import com.josedev.axity_consolidation_back.persistence.entity.ProductoEntity;
import com.josedev.axity_consolidation_back.web.dto.ProductoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(target = "sucursalProductos", source = "sucursalProductos")
    Producto entityToModel(ProductoEntity entity);

    @Mapping(target = "sucursalProductos", source = "sucursalProductos")
    ProductoEntity modelToEntity(Producto model);

    @Mapping(target = "codigoProducto", source = "codigoProducto")
    @Mapping(target = "nombreProducto", source = "nombreProducto")
    ProductoDTO modelToDto(Producto model);

    Producto dtoToModel(ProductoDTO dto);

    List<Producto> entityListToModelList(List<ProductoEntity> entities);

    List<ProductoDTO> modelListToDtoList(List<Producto> models);
}