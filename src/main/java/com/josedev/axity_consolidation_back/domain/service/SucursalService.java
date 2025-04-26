package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Sucursal;

import java.util.List;

public interface SucursalService {

    /**
     * Obtiene todas las sucursales
     * @return Lista de todas las sucursales
     */
    List<Sucursal> obtenerTodasLasSucursales();

    /**
     * Obtiene una sucursal por su código
     * @param codigoSucursal Código de la sucursal a buscar
     * @return Sucursal encontrada o null si no existe
     */
    Sucursal obtenerSucursalPorCodigo(String codigoSucursal);
}