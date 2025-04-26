package com.josedev.axity_consolidation_back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Resultados del proceso batch de verificación de conciliaciones
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoBatchResult {
    private int year;
    private int month;
    private int totalProcesados;
    private int totalDescuadrados;
    private LocalDateTime fechaProceso;
    private List<Conciliacion> conciliacionesDescuadradas;

    /**
     * Calcula el porcentaje de conciliaciones descuadradas
     * @return Porcentaje de descuadre o 0 si no hay conciliaciones procesadas
     */
    public double getPorcentajeDescuadradas() {
        if (totalProcesados == 0) return 0;
        return (double) totalDescuadrados / totalProcesados * 100;
    }

    /**
     * Verifica si el proceso encontró alguna conciliación descuadrada
     * @return true si hay conciliaciones descuadradas
     */
    public boolean tieneDescuadres() {
        return totalDescuadrados > 0;
    }

    /**
     * Obtiene el período procesado en formato texto (MM/YYYY)
     * @return Cadena con el mes y año procesados
     */
    public String getPeriodoProcesado() {
        return String.format("%02d/%d", month, year);
    }

    /**
     * Obtiene la fecha y hora de proceso en formato legible
     * @return Cadena con la fecha y hora formateadas
     */
    public String getFechaProcesoFormateada() {
        if (fechaProceso == null) return "N/A";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaProceso.format(formatter);
    }
}