package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    /**
     * Obtiene todos los productos
     * @return Lista de todos los productos
     */
    List<Producto> obtenerTodosLosProductos();

    /**
     * Obtiene un producto por su código
     * @param codigoProducto Código del producto a buscar
     * @return Optional conteniendo el producto si existe
     */
    Optional<Producto> obtenerProductoPorCodigo(String codigoProducto);

    /**
     * Guarda un producto (nuevo o existente)
     * @param producto Producto a guardar
     * @return Producto guardado
     * @throws IllegalArgumentException si el producto no cumple las validaciones
     */
    Producto guardarProducto(Producto producto);

    /**
     * Elimina un producto por su código
     * @param codigoProducto Código del producto a eliminar
     * @return true si el producto fue eliminado, false si no existía
     * @throws IllegalStateException si hay conciliaciones asociadas al producto
     */
    boolean eliminarProducto(String codigoProducto);
}