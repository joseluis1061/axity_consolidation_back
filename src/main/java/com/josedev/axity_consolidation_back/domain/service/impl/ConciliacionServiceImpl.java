package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;
import com.josedev.axity_consolidation_back.domain.service.ConciliacionService;
import com.josedev.axity_consolidation_back.persistence.entity.ConciliacionEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.ConciliacionMapper;
import com.josedev.axity_consolidation_back.persistence.repository.ConciliacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConciliacionServiceImpl implements ConciliacionService {

    private final ConciliacionRepository conciliacionRepository;
    private final ConciliacionMapper conciliacionMapper;

    private static final String ESTADO_DESCUADRADO = "D";

    @Override
    @Transactional(readOnly = true)
    public ProcesoBatchResult ejecutarProcesoBatch(int year, int month) {
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
    public List<Conciliacion> obtenerTodasLasConciliaciones() {
        log.info("Obteniendo todas las conciliaciones");

        List<ConciliacionEntity> conciliaciones = conciliacionRepository.findAll();
        log.info("Se encontraron {} conciliaciones en total", conciliaciones.size());

        // Mapear entidades a modelos de dominio
        return conciliacionMapper.entityListToModelList(conciliaciones);
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
}