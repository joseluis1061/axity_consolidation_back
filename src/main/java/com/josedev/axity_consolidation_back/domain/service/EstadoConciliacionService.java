package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;

import java.util.List;
import java.util.Optional;

public interface EstadoConciliacionService {

    /**
     * Obtiene todos los estados de conciliación
     * @return Lista de todos los estados de conciliación
     */
    List<EstadoConciliacion> obtenerTodosLosEstados();

    /**
     * Obtiene un estado de conciliación por su código
     * @param codigoEstado Código del estado a buscar
     * @return Optional conteniendo el estado si existe
     */
    Optional<EstadoConciliacion> obtenerEstadoPorCodigo(String codigoEstado);

    /**
     * Guarda un estado de conciliación (nuevo o existente)
     * @param estadoConciliacion Estado a guardar
     * @return Estado guardado
     * @throws IllegalArgumentException si el estado no cumple las validaciones
     */
    EstadoConciliacion guardarEstado(EstadoConciliacion estadoConciliacion);

    /**
     * Elimina un estado de conciliación por su código
     * @param codigoEstado Código del estado a eliminar
     * @return true si el estado fue eliminado, false si no existía
     * @throws IllegalStateException si hay conciliaciones asociadas al estado
     */
    boolean eliminarEstado(String codigoEstado);

    /**
     * Verifica si un código de estado es válido
     * @param codigoEstado Código a verificar
     * @return true si el código corresponde a un estado existente
     */
    boolean esEstadoValido(String codigoEstado);
}