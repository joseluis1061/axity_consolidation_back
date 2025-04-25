package com.josedev.axity_consolidation_back.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDTO {
    private String codigoDocumento;
    private String descripcion;
}