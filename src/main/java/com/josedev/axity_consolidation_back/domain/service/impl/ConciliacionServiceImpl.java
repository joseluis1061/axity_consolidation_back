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

    private final ConciliacionRepository conciliacionRepository;
    private final EstadoConciliacionRepository estadoConciliacionRepository;
    private final ConciliacionMapper conciliacionMapper;

    private static final String ESTADO_DESCUADRADO = "D";

    @Override
    @Transactional(readOnly = true)
    public ProcesoBatchResult ejecutarProcesoBatch(int year, int month) {
        if (year < 2000 || year > 2100 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Año o mes inválidos");
        }

        log.info("Ejecutando proceso batch para año {} y mes {}", year, month);

        // Obtener todas las conciliaciones del mes/año especificado
        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findByYearAndMonth(year, month);
        log.info("Se encontraron {} conciliaciones para el período especificado", conciliaciones.size());

        // Filtrar las conciliaciones con estado "D" (Descuadradas)
        List<ConciliacionEntity> conciliacionesDescuadradas = conciliaciones.stream()
                .filter(c -> ESTADO_DESCUADRADO.equals(c.getEstadoConciliacion().getCodigoEstado()))
                .collect(Collectors.toList());

        log.info("Se encontraron {} conciliaciones descuadradas", conciliacionesDescuadradas.size());

        // Mapear entidades a modelos de dominio
        List<Conciliacion> modelosConciliacionesDescuadradas = conciliacionMapper.entityListToModelList(conciliacionesDescuadradas);

        // Crear y devolver el resultado del proceso
        return ProcesoBatchResult.builder()
                .year(year)
                .month(month)
                .totalProcesados(conciliaciones.size())
                .totalDescuadrados(conciliacionesDescuadradas.size())
                .fechaProceso(LocalDateTime.now())
                .conciliacionesDescuadradas(modelosConciliacionesDescuadradas)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro) {
        log.info("Consultando conciliaciones con filtros: {}", filtro);

        if (filtro == null) {
            return obtenerTodasLasConciliaciones();
        }

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null &&
                filtro.getFechaInicio().isAfter(filtro.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }

        // Aplicar filtros de búsqueda
        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findByFiltros(
                filtro.getFechaInicio(),
                filtro.getFechaFin(),
                filtro.getCodigoSucursal(),
                filtro.getCodigoProducto(),
                filtro.getCodigoEstado()
        );

        log.info("Se encontraron {} conciliaciones con los filtros aplicados", conciliaciones.size());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro, Pageable pageable) {
        log.info("Consultando conciliaciones paginadas con filtros: {}", filtro);

        if (filtro == null) {
            return obtenerTodasLasConciliaciones(pageable);
        }

        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null &&
                filtro.getFechaInicio().isAfter(filtro.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }

        // Aplicar filtros de búsqueda con paginación
        Page<ConciliacionEntity> conciliacionesPage = conciliacionRepository.findByFiltrosPaginados(
                filtro.getFechaInicio(),
                filtro.getFechaFin(),
                filtro.getCodigoSucursal(),
                filtro.getCodigoProducto(),
                filtro.getCodigoEstado(),
                pageable
        );

        log.info("Se encontraron {} conciliaciones paginadas con los filtros aplicados",
                conciliacionesPage.getNumberOfElements());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityPageToModelPage(conciliacionesPage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> obtenerTodasLasConciliaciones() {
        log.info("Obteniendo todas las conciliaciones");

        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findAll();
        log.info("Se encontraron {} conciliaciones en total", conciliaciones.size());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Conciliacion> obtenerTodasLasConciliaciones(Pageable pageable) {
        log.info("Obteniendo todas las conciliaciones paginadas");

        Page<ConciliacionEntity> conciliacionesPage = conciliacionRepository.findAll(pageable);
        log.info("Se encontraron {} conciliaciones en la página {} de {}",
                conciliacionesPage.getNumberOfElements(),
                conciliacionesPage.getNumber() + 1,
                conciliacionesPage.getTotalPages());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityPageToModelPage(conciliacionesPage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> obtenerConciliacionesPorEstado(String codigoEstado) {
        log.info("Obteniendo conciliaciones con estado: {}", codigoEstado);

        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findByEstado(codigoEstado);
        log.info("Se encontraron {} conciliaciones con estado {}", conciliaciones.size(), codigoEstado);

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> obtenerConciliacionesDescuadradas() {
        log.info("Obteniendo conciliaciones descuadradas");

        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findConciliacionesDescuadradas();
        log.info("Se encontraron {} conciliaciones descuadradas", conciliaciones.size());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> obtenerConciliacionesPorFecha(LocalDate fecha) {
        log.info("Obteniendo conciliaciones para la fecha: {}", fecha);

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        // Aplicar filtros de búsqueda
        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findByFiltros(
                fecha, fecha, null, null, null
        );

        log.info("Se encontraron {} conciliaciones para la fecha {}", conciliaciones.size(), fecha);

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conciliacion> obtenerConciliacionesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        log.info("Obteniendo conciliaciones entre {} y {}", fechaInicio, fechaFin);

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }

        // Aplicar filtros de búsqueda
        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findByFiltros(
                fechaInicio, fechaFin, null, null, null
        );

        log.info("Se encontraron {} conciliaciones entre {} y {}",
                conciliaciones.size(), fechaInicio, fechaFin);

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Conciliacion> obtenerConciliacionPorId(Long idConciliacion) {
        log.info("Obteniendo conciliación con ID: {}", idConciliacion);

        if (idConciliacion == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        return conciliacionRepository.findById(idConciliacion)
                .map(conciliacionMapper::entityToModel);
    }

    @Override
    @Transactional
    public Conciliacion guardarConciliacion(Conciliacion conciliacion) {
        log.info("Guardando conciliación: {}", conciliacion);

        if (conciliacion == null) {
            throw new IllegalArgumentException("La conciliación no puede ser nula");
        }

        if (!conciliacion.esValida()) {
            throw new IllegalArgumentException("La conciliación no tiene datos válidos");
        }

        // Mapear modelo a entidad
        ConciliacionEntity entidad = conciliacionMapper.modelToEntity(conciliacion);

        // Si es una nueva conciliación, asignar fecha de creación
        if (entidad.getFechaCreacion() == null) {
            entidad.setFechaCreacion(LocalDateTime.now());
        }

        // Guardar entidad
        ConciliacionEntity guardada = conciliacionRepository.save(entidad);
        log.info("Conciliación guardada con ID: {}", guardada.getIdConciliacion());

        // Mapear entidad a modelo
        return conciliacionMapper.entityToModel(guardada);
    }

    @Override
    @Transactional
    public boolean actualizarEstadoConciliacion(Long idConciliacion, String codigoEstado) {
        log.info("Actualizando estado de conciliación ID: {} a estado: {}", idConciliacion, codigoEstado);

        if (idConciliacion == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (codigoEstado == null || codigoEstado.isEmpty()) {
            throw new IllegalArgumentException("El código de estado no puede ser nulo o vacío");
        }

        // Verificar que el estado existe
        Optional<EstadoConciliacionEntity> estadoOpt = estadoConciliacionRepository.findById(codigoEstado);
        if (estadoOpt.isEmpty()) {
            throw new IllegalArgumentException("El código de estado no es válido: " + codigoEstado);
        }

        // Verificar que la conciliación existe
        if (!conciliacionRepository.existsById(idConciliacion)) {
            log.warn("No se encontró conciliación con ID: {}", idConciliacion);
            return false;
        }

        // Actualizar estado
        conciliacionRepository.actualizarEstadoConciliacion(idConciliacion, codigoEstado);
        log.info("Estado actualizado correctamente");

        return true;
    }
}