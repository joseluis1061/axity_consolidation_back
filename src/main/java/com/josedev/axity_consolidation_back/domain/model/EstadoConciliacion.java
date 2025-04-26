package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa los posibles estados de una conciliación
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoConciliacion {
    /**
     * Código del estado:
     * A - Aceptada
     * B - Bajo revisión
     * C - Cuadrada
     * D - Descuadrada
     */
    private String codigoEstado;

    /**
     * Descripción textual del estado
     */
    private String descripcion;

    /**
     * Determina si este estado corresponde a una conciliación descuadrada
     * @return true si el estado es 'D' (Descuadrada)
     */
    public boolean esDescuadrada() {
        return "D".equals(codigoEstado);
    }

    /**
     * Determina si este estado corresponde a una conciliación cuadrada
     * @return true si el estado es 'C' (Cuadrada)
     */
    public boolean esCuadrada() {
        return "C".equals(codigoEstado);
    }

    /**
     * Determina si este estado requiere revisión
     * @return true si el estado es 'B' (Bajo revisión)
     */
    public boolean requiereRevision() {
        return "B".equals(codigoEstado);
    }

    /**
     * Determina si este estado indica que la conciliación está aceptada
     * @return true si el estado es 'A' (Aceptada)
     */
    public boolean esAceptada() {
        return "A".equals(codigoEstado);
    }
}