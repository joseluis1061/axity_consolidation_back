package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Filtros para la búsqueda de conciliaciones
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConciliacionFiltro {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String codigoSucursal;
    private String codigoProducto;
    private String codigoEstado;

    /**
     * Verifica si hay algún criterio de filtro activo
     * @return true si al menos un criterio de filtro está definido
     */
    public boolean tieneFiltrosActivos() {
        return fechaInicio != null || fechaFin != null ||
                (codigoSucursal != null && !codigoSucursal.isEmpty()) ||
                (codigoProducto != null && !codigoProducto.isEmpty()) ||
                (codigoEstado != null && !codigoEstado.isEmpty());
    }

    /**
     * Valida que los filtros sean coherentes
     * @return true si los filtros son válidos
     */
    public boolean esValido() {
        // Si hay fecha inicio y fin, inicio debe ser anterior o igual a fin
        if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            return false;
        }
        return true;
    }

    /**
     * Genera una descripción de los filtros aplicados
     * @return Cadena descriptiva de los filtros activos
     */
    public String getDescripcionFiltros() {
        StringBuilder sb = new StringBuilder("Filtros: ");
        boolean hayFiltros = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (fechaInicio != null) {
            sb.append("desde ").append(fechaInicio.format(formatter));
            hayFiltros = true;
        }

        if (fechaFin != null) {
            if (hayFiltros) sb.append(", ");
            sb.append("hasta ").append(fechaFin.format(formatter));
            hayFiltros = true;
        }

        if (codigoSucursal != null && !codigoSucursal.isEmpty()) {
            if (hayFiltros) sb.append(", ");
            sb.append("sucursal: ").append(codigoSucursal);
            hayFiltros = true;
        }

        if (codigoProducto != null && !codigoProducto.isEmpty()) {
            if (hayFiltros) sb.append(", ");
            sb.append("producto: ").append(codigoProducto);
            hayFiltros = true;
        }

        if (codigoEstado != null && !codigoEstado.isEmpty()) {
            if (hayFiltros) sb.append(", ");
            sb.append("estado: ").append(codigoEstado);
            hayFiltros = true;
        }

        if (!hayFiltros) {
            sb.append("ninguno");
        }

        return sb.toString();
    }
}