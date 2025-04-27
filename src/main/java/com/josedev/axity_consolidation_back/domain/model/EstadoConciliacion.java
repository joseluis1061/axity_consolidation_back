package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de dominio que representa un estado de conciliación en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoConciliacion {

    /**
     * Código que identifica el estado de conciliación.
     * Puede ser: A (Aceptada), B (Bajo revisión), C (Cuadrada), D (Descuadrada).
     */
    private String codigoEstado;

    /**
     * Descripción textual del estado de conciliación.
     */
    private String descripcion;
}

/*
    private String codigoEstado;
    private String descripcion;

    // Relaciones - añadidas para coincidir con la entidad
    private List<Conciliacion> conciliaciones;
 */