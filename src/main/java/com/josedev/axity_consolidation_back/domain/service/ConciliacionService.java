package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;

import java.util.List;

public interface ConciliacionService {

    /**
     * Ejecuta el proceso batch que identifica conciliaciones descuadradas (API1)
     * @param year Año a procesar
     * @param month Mes a procesar
     * @return Resultado del proceso batch
     */
    ProcesoBatchResult ejecutarProcesoBatch(int year, int month);

    /**
     * Consulta conciliaciones según filtros aplicados (API2)
     * @param filtro Filtros de búsqueda
     * @return Lista de conciliaciones que cumplen con los filtros
     */
    List<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro);

    /**
     * Obtiene todas las conciliaciones
     * @return Lista de todas las conciliaciones
     */
    List<Conciliacion> obtenerTodasLasConciliaciones();

    /**
     * Obtiene conciliaciones por estado
     * @param codigoEstado Código del estado a filtrar
     * @return Lista de conciliaciones con el estado especificado
     */
    List<Conciliacion> obtenerConciliacionesPorEstado(String codigoEstado);
}