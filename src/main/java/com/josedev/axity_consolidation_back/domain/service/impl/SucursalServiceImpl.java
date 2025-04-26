package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;
import com.josedev.axity_consolidation_back.domain.service.SucursalService;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.SucursalMapper;
import com.josedev.axity_consolidation_back.persistence.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> obtenerTodasLasSucursales() {
        log.info("Obteniendo todas las sucursales");
        return sucursalMapper.entityListToModelList(sucursalRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sucursal> obtenerSucursalPorCodigo(String codigoSucursal) {
        log.info("Obteniendo sucursal con código: {}", codigoSucursal);

        if (codigoSucursal == null || codigoSucursal.isEmpty()) {
            throw new IllegalArgumentException("El código de sucursal no puede ser nulo o vacío");
        }

        return sucursalRepository.findById(codigoSucursal)
                .map(sucursalMapper::entityToModel);
    }

    @Override
    @Transactional
    public Sucursal guardarSucursal(Sucursal sucursal) {
        log.info("Guardando sucursal: {}", sucursal);

        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal no puede ser nula");
        }

        if (sucursal.getCodigoSucursal() == null || sucursal.getCodigoSucursal().isEmpty()) {
            throw new IllegalArgumentException("El código de sucursal no puede ser nulo o vacío");
        }

        if (sucursal.getNombreSucursal() == null || sucursal.getNombreSucursal().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sucursal no puede ser nulo o vacío");
        }

        SucursalEntity entidad = sucursalMapper.modelToEntity(sucursal);
        SucursalEntity guardada = sucursalRepository.save(entidad);

        log.info("Sucursal guardada con código: {}", guardada.getCodigoSucursal());
        return sucursalMapper.entityToModel(guardada);
    }

    @Override
    @Transactional
    public boolean eliminarSucursal(String codigoSucursal) {
        log.info("Eliminando sucursal con código: {}", codigoSucursal);

        if (codigoSucursal == null || codigoSucursal.isEmpty()) {
            throw new IllegalArgumentException("El código de sucursal no puede ser nulo o vacío");
        }

        if (!sucursalRepository.existsById(codigoSucursal)) {
            log.warn("No se encontró sucursal con código: {}", codigoSucursal);
            return false;
        }

        try {
            sucursalRepository.deleteById(codigoSucursal);
            log.info("Sucursal eliminada correctamente");
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar sucursal: {}", e.getMessage());
            throw new IllegalStateException("No se puede eliminar la sucursal porque está siendo utilizada", e);
        }
    }
}