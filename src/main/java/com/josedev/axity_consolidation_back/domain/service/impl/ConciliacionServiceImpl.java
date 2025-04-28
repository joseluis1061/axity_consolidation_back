package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;
import com.josedev.axity_consolidation_back.domain.service.ConciliacionService;
import com.josedev.axity_consolidation_back.persistence.entity.ConciliacionEntity;
import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.ConciliacionMapper;
import com.josedev.axity_consolidation_back.persistence.repository.ConciliacionRepository;
import com.josedev.axity_consolidation_back.persistence.repository.EstadoConciliacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConciliacionServiceImpl implements ConciliacionService {

    @Override
    public ProcesoBatchResult ejecutarProcesoBatch(int year, int month) {
        return null;
    }

    @Override
    public List<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro) {
        return List.of();
    }

    @Override
    public Page<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro, Pageable pageable) {
        return null;
    }

    @Override
    public List<Conciliacion> obtenerTodasLasConciliaciones() {
        return List.of();
    }

    @Override
    public Page<Conciliacion> obtenerTodasLasConciliaciones(Pageable pageable) {
        return null;
    }

    @Override
    public List<Conciliacion> obtenerConciliacionesPorEstado(String codigoEstado) {
        return List.of();
    }

    @Override
    public List<Conciliacion> obtenerConciliacionesDescuadradas() {
        return List.of();
    }

    @Override
    public List<Conciliacion> obtenerConciliacionesPorFecha(LocalDate fecha) {
        return List.of();
    }

    @Override
    public List<Conciliacion> obtenerConciliacionesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return List.of();
    }

    @Override
    public Optional<Conciliacion> obtenerConciliacionPorId(Long idConciliacion) {
        return Optional.empty();
    }

    @Override
    public Conciliacion guardarConciliacion(Conciliacion conciliacion) {
        return null;
    }

    @Override
    public boolean actualizarEstadoConciliacion(Long idConciliacion, String codigoEstado) {
        return false;
    }
}