package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Producto;

import java.util.List;

public interface ProductoService {

    /**
     * Obtiene todos los productos
     * @return Lista de todos los productos
     */
    List<Producto> obtenerTodosLosProductos();

    /**
     * Obtiene un producto por su código
     * @param codigoProducto Código del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    Producto obtenerProductoPorCodigo(String codigoProducto);
}