package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Conciliacion;
import com.josedev.axity_consolidation_back.domain.model.ConciliacionFiltro;
import com.josedev.axity_consolidation_back.domain.model.ProcesoBatchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConciliacionService {

    /**
     * Ejecuta el proceso batch que identifica conciliaciones descuadradas (API1)
     * @param year Año a procesar
     * @param month Mes a procesar
     * @return Resultado del proceso batch
     * @throws IllegalArgumentException si el año o mes son inválidos
     */
    ProcesoBatchResult ejecutarProcesoBatch(int year, int month);

    /**
     * Consulta conciliaciones según filtros aplicados (API2)
     * @param filtro Filtros de búsqueda
     * @return Lista de conciliaciones que cumplen con los filtros
     */
    List<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro);

    /**
     * Consulta conciliaciones según filtros aplicados con soporte para paginación
     * @param filtro Filtros de búsqueda
     * @param pageable Información de paginación
     * @return Página de conciliaciones que cumplen con los filtros
     */
    Page<Conciliacion> consultarConciliacionesFiltradas(ConciliacionFiltro filtro, Pageable pageable);

    /**
     * Obtiene todas las conciliaciones
     * @return Lista de todas las conciliaciones
     */
    List<Conciliacion> obtenerTodasLasConciliaciones();

    /**
     * Obtiene todas las conciliaciones con paginación
     * @param pageable Información de paginación
     * @return Página de conciliaciones
     */
    Page<Conciliacion> obtenerTodasLasConciliaciones(Pageable pageable);

    /**
     * Obtiene conciliaciones por estado
     * @param codigoEstado Código del estado a filtrar
     * @return Lista de conciliaciones con el estado especificado
     */
    List<Conciliacion> obtenerConciliacionesPorEstado(String codigoEstado);

    /**
     * Obtiene conciliaciones descuadradas (estado 'D')
     * @return Lista de conciliaciones descuadradas
     */
    List<Conciliacion> obtenerConciliacionesDescuadradas();

    /**
     * Obtiene conciliaciones por fecha
     * @param fecha Fecha de conciliación a buscar
     * @return Lista de conciliaciones para la fecha especificada
     */
    List<Conciliacion> obtenerConciliacionesPorFecha(LocalDate fecha);

    /**
     * Obtiene conciliaciones por rango de fechas
     * @param fechaInicio Fecha inicial del rango
     * @param fechaFin Fecha final del rango
     * @return Lista de conciliaciones dentro del rango de fechas
     * @throws IllegalArgumentException si fechaInicio es posterior a fechaFin
     */
    List<Conciliacion> obtenerConciliacionesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Obtiene una conciliación por su ID
     * @param idConciliacion ID de la conciliación a buscar
     * @return Optional conteniendo la conciliación si existe
     */
    Optional<Conciliacion> obtenerConciliacionPorId(Long idConciliacion);

    /**
     * Guarda una conciliación (nueva o existente)
     * @param conciliacion Conciliación a guardar
     * @return Conciliación guardada con ID asignado
     * @throws IllegalArgumentException si la conciliación no cumple las validaciones
     */
    Conciliacion guardarConciliacion(Conciliacion conciliacion);

    /**
     * Actualiza el estado de una conciliación
     * @param idConciliacion ID de la conciliación a actualizar
     * @param codigoEstado Nuevo código de estado
     * @return true si la actualización fue exitosa
     * @throws IllegalArgumentException si el ID o código de estado son inválidos
     */
    boolean actualizarEstadoConciliacion(Long idConciliacion, String codigoEstado);
}