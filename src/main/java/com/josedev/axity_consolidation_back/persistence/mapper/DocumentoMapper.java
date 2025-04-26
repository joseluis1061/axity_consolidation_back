package com.josedev.axity_consolidation_back.persistence.mapper;

import com.josedev.axity_consolidation_back.domain.model.Documento;
import com.josedev.axity_consolidation_back.persistence.entity.DocumentoEntity;
import com.josedev.axity_consolidation_back.web.dto.DocumentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

    @Mapping(target = "sucursalProductos", ignore = true)
    Documento entityToModel(DocumentoEntity entity);

    @Mapping(target = "sucursalProductos", ignore = true)
    DocumentoEntity modelToEntity(Documento model);

    DocumentoDTO modelToDto(Documento model);

    Documento dtoToModel(DocumentoDTO dto);

    List<Documento> entityListToModelList(List<DocumentoEntity> entities);

    List<DocumentoDTO> modelListToDtoList(List<Documento> models);
}