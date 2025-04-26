package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;
import com.josedev.axity_consolidation_back.domain.service.SucursalService;
import com.josedev.axity_consolidation_back.persistence.mapper.SucursalMapper;
import com.josedev.axity_consolidation_back.persistence.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Sucursal obtenerSucursalPorCodigo(String codigoSucursal) {
        log.info("Obteniendo sucursal con código: {}", codigoSucursal);
        return sucursalRepository.findById(codigoSucursal)
                .map(sucursalMapper::entityToModel)
                .orElse(null);
    }
}