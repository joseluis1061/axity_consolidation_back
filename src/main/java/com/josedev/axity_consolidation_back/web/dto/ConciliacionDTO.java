package com.josedev.axity_consolidation_back.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConciliacionDTO {
    private Long idConciliacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaConciliacion;

    private String codigoSucursal;
    private String nombreSucursal;
    private String codigoProducto;
    private String nombreProducto;
    private String codigoDocumento;
    private BigDecimal diferenciaFisica;
    private BigDecimal diferenciaValor;
    private String codigoEstado;
    private String descripcionEstado;
}
