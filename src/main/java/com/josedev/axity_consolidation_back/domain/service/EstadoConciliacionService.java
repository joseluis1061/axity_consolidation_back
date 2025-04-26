package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;

import java.util.List;

public interface EstadoConciliacionService {

    /**
     * Obtiene todos los estados de conciliación
     * @return Lista de todos los estados de conciliación
     */
    List<EstadoConciliacion> obtenerTodosLosEstados();

    /**
     * Obtiene un estado de conciliación por su código
     * @param codigoEstado Código del estado a buscar
     * @return Estado de conciliación encontrado o null si no existe
     */
    EstadoConciliacion obtenerEstadoPorCodigo(String codigoEstado);
}