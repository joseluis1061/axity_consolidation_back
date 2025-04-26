package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;

import java.util.List;
import java.util.Optional;

public interface SucursalService {

    /**
     * Obtiene todas las sucursales
     * @return Lista de todas las sucursales
     */
    List<Sucursal> obtenerTodasLasSucursales();

    /**
     * Obtiene una sucursal por su código
     * @param codigoSucursal Código de la sucursal a buscar
     * @return Optional conteniendo la sucursal si existe
     */
    Optional<Sucursal> obtenerSucursalPorCodigo(String codigoSucursal);

    /**
     * Guarda una sucursal (nueva o existente)
     * @param sucursal Sucursal a guardar
     * @return Sucursal guardada
     * @throws IllegalArgumentException si la sucursal no cumple las validaciones
     */
    Sucursal guardarSucursal(Sucursal sucursal);

    /**
     * Elimina una sucursal por su código
     * @param codigoSucursal Código de la sucursal a eliminar
     * @return true si la sucursal fue eliminada, false si no existía
     * @throws IllegalStateException si hay conciliaciones asociadas a la sucursal
     */
    boolean eliminarSucursal(String codigoSucursal);
}