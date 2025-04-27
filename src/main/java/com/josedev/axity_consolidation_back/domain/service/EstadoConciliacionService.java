package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de servicio para la entidad EstadoConciliacion.
 */
public interface EstadoConciliacionService {

    /**
     * Obtiene todos los estados de conciliación disponibles
     *
     * @return Lista de estados de conciliación
     */
    List<EstadoConciliacion> getAllEstadosConciliacion();

    /**
     * Busca un estado de conciliación por su código
     *
     * @param codigoEstado Código único del estado de conciliación
     * @return Optional con el estado de conciliación si existe, empty si no
     */
    Optional<EstadoConciliacion> getEstadoConciliacionById(String codigoEstado);

    /**
     * Guarda un nuevo estado de conciliación
     *
     * @param estadoConciliacion El estado de conciliación a guardar
     * @return El estado de conciliación guardado con posibles modificaciones
     */
    EstadoConciliacion saveEstadoConciliacion(EstadoConciliacion estadoConciliacion);

    /**
     * Actualiza un estado de conciliación existente
     *
     * @param codigoEstado Código del estado de conciliación a actualizar
     * @param estadoConciliacion Datos actualizados del estado de conciliación
     * @return El estado de conciliación actualizado o Optional.empty() si no se encontró
     */
    Optional<EstadoConciliacion> updateEstadoConciliacion(String codigoEstado, EstadoConciliacion estadoConciliacion);

    /**
     * Elimina un estado de conciliación por su código
     *
     * @param codigoEstado Código del estado de conciliación a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    boolean deleteEstadoConciliacion(String codigoEstado);

    /**
     * Verifica si existe un estado de conciliación con el código especificado
     *
     * @param codigoEstado Código del estado de conciliación a verificar
     * @return true si existe, false si no
     */
    boolean existsEstadoConciliacion(String codigoEstado);
}
/*
    List<EstadoConciliacion> obtenerTodosLosEstados();

    Optional<EstadoConciliacion> obtenerEstadoPorCodigo(String codigoEstado);

    EstadoConciliacion guardarEstado(EstadoConciliacion estadoConciliacion);


    boolean eliminarEstado(String codigoEstado);

    boolean esEstadoValido(String codigoEstado);
 */