package com.josedev.axity_consolidation_back.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para la entidad EstadoConciliacion.
 * Define la estructura de los datos que se intercambian con los clientes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoConciliacionDTO {

    /**
     * Código único del estado de conciliación.
     * No puede estar vacío y debe tener un formato válido.
     */
    @NotBlank(message = "El código del estado de conciliación es obligatorio")
    @Size(min = 1, max = 2, message = "El código del estado de conciliación debe tener entre 1 y 2 caracteres")
    @Pattern(regexp = "^[A-Z]$|^[A-Z][0-9]$", message = "El código del estado debe ser una letra mayúscula, opcionalmente seguida de un número")
    private String codigoEstado;

    /**
     * Descripción del estado de conciliación.
     * No puede estar vacía.
     */
    @NotBlank(message = "La descripción del estado de conciliación es obligatorio")
    @Size(max = 50, message = "La descripción del estado de conciliación no puede exceder los 50 caracteres")
    private String descripcion;
}

    /*
    private String codigoEstado;
    private String descripcion;

     */
