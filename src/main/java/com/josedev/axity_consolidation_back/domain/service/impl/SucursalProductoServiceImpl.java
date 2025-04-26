package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.SucursalProducto;
import com.josedev.axity_consolidation_back.domain.model.SucursalProductoId;
import com.josedev.axity_consolidation_back.domain.service.SucursalProductoService;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.SucursalProductoIdMapper;
import com.josedev.axity_consolidation_back.persistence.mapper.SucursalProductoMapper;
import com.josedev.axity_consolidation_back.persistence.repository.SucursalProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SucursalProductoServiceImpl implements SucursalProductoService {

    private final SucursalProductoRepository sucursalProductoRepository;
    private final SucursalProductoMapper sucursalProductoMapper;
    private final SucursalProductoIdMapper sucursalProductoIdMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SucursalProducto> obtenerTodasLasRelaciones() {
        log.info("Obteniendo todas las relaciones sucursal-producto");
        return sucursalProductoMapper.entityListToModelList(sucursalProductoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SucursalProducto> obtenerRelacionPorId(SucursalProductoId id) {
        log.info("Obteniendo relación sucursal-producto con ID: {}", id);

        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (id.getCodigoSucursal() == null || id.getCodigoProducto() == null || id.getCodigoDocumento() == null) {
            throw new IllegalArgumentException("Los códigos en el ID compuesto no pueden ser nulos");
        }

        com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoId entityId =
                sucursalProductoIdMapper.modelToEntity(id);

        return sucursalProductoRepository.findById(entityId)
                .map(sucursalProductoMapper::entityToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalProducto> obtenerRelacionesPorSucursal(String codigoSucursal) {
        log.info("Obteniendo relaciones para la sucursal: {}", codigoSucursal);

        if (codigoSucursal == null || codigoSucursal.isEmpty()) {
            throw new IllegalArgumentException("El código de sucursal no puede ser nulo o vacío");
        }

        return sucursalProductoRepository.findAll().stream()
                .filter(sp -> codigoSucursal.equals(sp.getSucursal().getCodigoSucursal()))
                .map(sucursalProductoMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalProducto> obtenerRelacionesPorProducto(String codigoProducto) {
        log.info("Obteniendo relaciones para el producto: {}", codigoProducto);

        if (codigoProducto == null || codigoProducto.isEmpty()) {
            throw new IllegalArgumentException("El código de producto no puede ser nulo o vacío");
        }

        return sucursalProductoRepository.findAll().stream()
                .filter(sp -> codigoProducto.equals(sp.getProducto().getCodigoProducto()))
                .map(sucursalProductoMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SucursalProducto guardarRelacion(SucursalProducto sucursalProducto) {
        log.info("Guardando relación sucursal-producto: {}", sucursalProducto);

        if (sucursalProducto == null) {
            throw new IllegalArgumentException("La relación sucursal-producto no puede ser nula");
        }

        if (sucursalProducto.getId() == null) {
            throw new IllegalArgumentException("El ID compuesto no puede ser nulo");
        }

        SucursalProductoEntity entidad = sucursalProductoMapper.modelToEntity(sucursalProducto);
        SucursalProductoEntity guardada = sucursalProductoRepository.save(entidad);

        log.info("Relación guardada con ID: {}", guardada.getId());
        return sucursalProductoMapper.entityToModel(guardada);
    }

    @Override
    @Transactional
    public boolean eliminarRelacion(SucursalProductoId id) {
        log.info("Eliminando relación con ID: {}", id);

        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoId entityId =
                sucursalProductoIdMapper.modelToEntity(id);

        if (!sucursalProductoRepository.existsById(entityId)) {
            log.warn("No se encontró relación con ID: {}", id);
            return false;
        }

        try {
            sucursalProductoRepository.deleteById(entityId);
            log.info("Relación eliminada correctamente");
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar relación: {}", e.getMessage());
            throw new IllegalStateException("No se puede eliminar la relación porque está siendo utilizada", e);
        }
    }
}