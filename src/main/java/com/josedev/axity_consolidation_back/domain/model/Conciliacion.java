package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conciliacion {
    private Long idConciliacion;
    private LocalDate fechaConciliacion;
    private SucursalProducto sucursalProducto;
    private BigDecimal diferenciaFisica;
    private BigDecimal diferenciaValor;
    private EstadoConciliacion estadoConciliacion;
    private LocalDateTime fechaCreacion;

    // Campos derivados para simplificar el acceso a la información
    private String codigoSucursal;
    private String nombreSucursal;
    private String codigoProducto;
    private String nombreProducto;
    private String codigoDocumento;
    private String codigoEstado;
    private String descripcionEstado;

    // Referencias simplificadas
    private Sucursal sucursal;
    private Producto producto;
    private Documento documento;
}