package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalProductoId {
    private String codigoSucursal;
    private String codigoProducto;
    private String codigoDocumento;
}