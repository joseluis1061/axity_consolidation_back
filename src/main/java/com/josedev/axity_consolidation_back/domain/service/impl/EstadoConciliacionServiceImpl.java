package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.domain.service.EstadoConciliacionService;
import com.josedev.axity_consolidation_back.persistence.mapper.EstadoConciliacionMapper;
import com.josedev.axity_consolidation_back.persistence.repository.EstadoConciliacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstadoConciliacionServiceImpl implements EstadoConciliacionService {

    private final EstadoConciliacionRepository estadoConciliacionRepository;
    private final EstadoConciliacionMapper estadoConciliacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EstadoConciliacion> obtenerTodosLosEstados() {
        log.info("Obteniendo todos los estados de conciliación");
        return estadoConciliacionMapper.entityListToModelList(estadoConciliacionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoConciliacion obtenerEstadoPorCodigo(String codigoEstado) {
        log.info("Obteniendo estado de conciliación con código: {}", codigoEstado);
        return estadoConciliacionRepository.findById(codigoEstado)
                .map(estadoConciliacionMapper::entityToModel)
                .orElse(null);
    }
}