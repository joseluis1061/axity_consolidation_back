package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.SucursalProducto;
import com.josedev.axity_consolidation_back.domain.model.SucursalProductoId;

import java.util.List;
import java.util.Optional;

public interface SucursalProductoService {

    /**
     * Obtiene todas las relaciones sucursal-producto
     * @return Lista de todas las relaciones
     */
    List<SucursalProducto> obtenerTodasLasRelaciones();

    /**
     * Obtiene una relación sucursal-producto por su ID compuesto
     * @param id ID compuesto con códigos de sucursal, producto y documento
     * @return Optional conteniendo la relación si existe
     */
    Optional<SucursalProducto> obtenerRelacionPorId(SucursalProductoId id);

    /**
     * Obtiene relaciones por código de sucursal
     * @param codigoSucursal Código de la sucursal
     * @return Lista de relaciones para la sucursal especificada
     */
    List<SucursalProducto> obtenerRelacionesPorSucursal(String codigoSucursal);

    /**
     * Obtiene relaciones por código de producto
     * @param codigoProducto Código del producto
     * @return Lista de relaciones para el producto especificado
     */
    List<SucursalProducto> obtenerRelacionesPorProducto(String codigoProducto);

    /**
     * Guarda una relación sucursal-producto (nueva o existente)
     * @param sucursalProducto Relación a guardar
     * @return Relación guardada
     * @throws IllegalArgumentException si la relación no cumple las validaciones
     */
    SucursalProducto guardarRelacion(SucursalProducto sucursalProducto);

    /**
     * Elimina una relación por su ID compuesto
     * @param id ID compuesto de la relación a eliminar
     * @return true si la relación fue eliminada, false si no existía
     * @throws IllegalStateException si hay conciliaciones asociadas a la relación
     */
    boolean eliminarRelacion(SucursalProductoId id);
}