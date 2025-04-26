package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.domain.service.EstadoConciliacionService;
import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.EstadoConciliacionMapper;
import com.josedev.axity_consolidation_back.persistence.repository.EstadoConciliacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<EstadoConciliacion> obtenerEstadoPorCodigo(String codigoEstado) {
        log.info("Obteniendo estado de conciliación con código: {}", codigoEstado);

        if (codigoEstado == null || codigoEstado.isEmpty()) {
            throw new IllegalArgumentException("El código de estado no puede ser nulo o vacío");
        }

        return estadoConciliacionRepository.findById(codigoEstado)
                .map(estadoConciliacionMapper::entityToModel);
    }

    @Override
    @Transactional
    public EstadoConciliacion guardarEstado(EstadoConciliacion estadoConciliacion) {
        log.info("Guardando estado de conciliación: {}", estadoConciliacion);

        if (estadoConciliacion == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }

        if (estadoConciliacion.getCodigoEstado() == null || estadoConciliacion.getCodigoEstado().isEmpty()) {
            throw new IllegalArgumentException("El código de estado no puede ser nulo o vacío");
        }

        if (estadoConciliacion.getDescripcion() == null || estadoConciliacion.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }

        EstadoConciliacionEntity entidad = estadoConciliacionMapper.modelToEntity(estadoConciliacion);
        EstadoConciliacionEntity guardado = estadoConciliacionRepository.save(entidad);

        log.info("Estado guardado con código: {}", guardado.getCodigoEstado());
        return estadoConciliacionMapper.entityToModel(guardado);
    }

    @Override
    @Transactional
    public boolean eliminarEstado(String codigoEstado) {
        log.info("Eliminando estado con código: {}", codigoEstado);

        if (codigoEstado == null || codigoEstado.isEmpty()) {
            throw new IllegalArgumentException("El código de estado no puede ser nulo o vacío");
        }

        if (!estadoConciliacionRepository.existsById(codigoEstado)) {
            log.warn("No se encontró estado con código: {}", codigoEstado);
            return false;
        }

        try {
            estadoConciliacionRepository.deleteById(codigoEstado);
            log.info("Estado eliminado correctamente");
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar estado: {}", e.getMessage());
            throw new IllegalStateException("No se puede eliminar el estado porque está siendo utilizado", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean esEstadoValido(String codigoEstado) {
        if (codigoEstado == null || codigoEstado.isEmpty()) {
            return false;
        }

        return estadoConciliacionRepository.existsById(codigoEstado);
    }
}