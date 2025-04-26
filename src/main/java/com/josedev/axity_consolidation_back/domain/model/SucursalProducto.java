package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalProducto {
    private SucursalProductoId id;
    private Sucursal sucursal;
    private Producto producto;
    private Documento documento;

    // Relaciones - añadidas para coincidir con la entidad
    private List<Conciliacion> conciliaciones;
}