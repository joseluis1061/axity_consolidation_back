package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa una conciliación entre el inventario físico y virtual de productos
 * en una sucursal específica y para una fecha determinada.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conciliacion {
    private Long idConciliacion;

    /**
     * Fecha en que se realizó la conciliación
     */
    private LocalDate fechaConciliacion;

    /**
     * Relación con la combinación sucursal-producto-documento
     */
    private SucursalProducto sucursalProducto;

    /**
     * Diferencia encontrada en unidades físicas
     */
    private BigDecimal diferenciaFisica;

    /**
     * Diferencia encontrada en valores monetarios
     */
    private BigDecimal diferenciaValor;

    /**
     * Estado actual de la conciliación
     */
    private EstadoConciliacion estadoConciliacion;

    /**
     * Fecha y hora de creación del registro
     */
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

    /**
     * Determina si la conciliación está descuadrada basado en su estado
     * @return true si la conciliación está descuadrada (estado 'D')
     */
    public boolean isDescuadrada() {
        return "D".equals(codigoEstado);
    }

    /**
     * Determina si la conciliación está cuadrada basado en su estado
     * @return true si la conciliación está cuadrada (estado 'C')
     */
    public boolean isCuadrada() {
        return "C".equals(codigoEstado);
    }

    /**
     * Evalúa si hay diferencias significativas tanto físicas como de valor
     * @return true si ambas diferencias son mayores que cero
     */
    public boolean tieneDiferenciaSignificativa() {
        return (diferenciaFisica != null && diferenciaFisica.compareTo(BigDecimal.ZERO) > 0) &&
                (diferenciaValor != null && diferenciaValor.compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * Evalúa si esta conciliación cumple con los criterios de filtro especificados
     * @param filtro Criterios de filtro a aplicar
     * @return true si la conciliación cumple con todos los criterios aplicables
     */
    public boolean coincideConFiltro(ConciliacionFiltro filtro) {
        if (filtro == null) return true;

        // Verificar fecha de inicio
        if (filtro.getFechaInicio() != null &&
                (fechaConciliacion == null || fechaConciliacion.isBefore(filtro.getFechaInicio()))) {
            return false;
        }

        // Verificar fecha de fin
        if (filtro.getFechaFin() != null &&
                (fechaConciliacion == null || fechaConciliacion.isAfter(filtro.getFechaFin()))) {
            return false;
        }

        // Verificar sucursal
        if (filtro.getCodigoSucursal() != null && !filtro.getCodigoSucursal().equals(codigoSucursal)) {
            return false;
        }

        // Verificar producto
        if (filtro.getCodigoProducto() != null && !filtro.getCodigoProducto().equals(codigoProducto)) {
            return false;
        }

        // Verificar estado
        if (filtro.getCodigoEstado() != null && !filtro.getCodigoEstado().equals(codigoEstado)) {
            return false;
        }

        return true;
    }

    /**
     * Valida si los datos de la conciliación son correctos
     * @return true si todos los datos obligatorios están presentes y son válidos
     */
    public boolean esValida() {
        // Fecha de conciliación es obligatoria
        if (fechaConciliacion == null) {
            return false;
        }

        // Diferencias no pueden ser null
        if (diferenciaFisica == null || diferenciaValor == null) {
            return false;
        }

        // Debe tener un estado
        if (codigoEstado == null || codigoEstado.isEmpty()) {
            return false;
        }

        // Debe tener referencias a sucursal y producto
        return codigoSucursal != null && !codigoSucursal.isEmpty() &&
                codigoProducto != null && !codigoProducto.isEmpty();
    }
}