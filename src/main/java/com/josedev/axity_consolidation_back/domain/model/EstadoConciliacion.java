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
public class EstadoConciliacion {
    private String codigoEstado;
    private String descripcion;

    // Relaciones - añadidas para coincidir con la entidad
    private List<Conciliacion> conciliaciones;
}