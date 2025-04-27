package com.josedev.axity_consolidation_back.web.controller;

import com.josedev.axity_consolidation_back.domain.model.EstadoConciliacion;
import com.josedev.axity_consolidation_back.domain.service.EstadoConciliacionService;
import com.josedev.axity_consolidation_back.web.dto.EstadoConciliacionDTO;
import com.josedev.axity_consolidation_back.web.mapper.EstadoConciliacionDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de estados de conciliación.
 */
@RestController
@RequestMapping("/estados-conciliacion")
@Tag(name = "Estados de Conciliación", description = "API para la gestión de estados de conciliación")
public class EstadoConciliacionController {

    private final EstadoConciliacionService estadoConciliacionService;
    private final EstadoConciliacionDTOMapper estadoConciliacionDTOMapper;

    @Autowired
    public EstadoConciliacionController(EstadoConciliacionService estadoConciliacionService,
                                        EstadoConciliacionDTOMapper estadoConciliacionDTOMapper) {
        this.estadoConciliacionService = estadoConciliacionService;
        this.estadoConciliacionDTOMapper = estadoConciliacionDTOMapper;
    }

    /**
     * Obtiene todos los estados de conciliación.
     *
     * @return Lista de estados de conciliación
     */
    @GetMapping
    @Operation(summary = "Obtener todos los estados de conciliación",
            description = "Retorna una lista con todos los estados de conciliación disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = EstadoConciliacionDTO.class)))
    })
    public ResponseEntity<List<EstadoConciliacionDTO>> getAllEstadosConciliacion() {
        List<EstadoConciliacion> estadosConciliacion = estadoConciliacionService.getAllEstadosConciliacion();
        List<EstadoConciliacionDTO> estadosConciliacionDTO = estadoConciliacionDTOMapper.toEstadoConciliacionDTOList(estadosConciliacion);
        return ResponseEntity.ok(estadosConciliacionDTO);
    }

    /**
     * Obtiene un estado de conciliación por su código.
     *
     * @param codigoEstado Código del estado de conciliación
     * @return Estado de conciliación encontrado o 404 si no existe
     */
    @GetMapping("/{codigoEstado}")
    @Operation(summary = "Obtener un estado de conciliación por su código",
            description = "Retorna un estado de conciliación según el código proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = EstadoConciliacionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Estado de conciliación no encontrado")
    })
    public ResponseEntity<EstadoConciliacionDTO> getEstadoConciliacionById(@PathVariable String codigoEstado) {
        Optional<EstadoConciliacion> estadoConciliacion = estadoConciliacionService.getEstadoConciliacionById(codigoEstado);

        return estadoConciliacion
                .map(estado -> ResponseEntity.ok(estadoConciliacionDTOMapper.toEstadoConciliacionDTO(estado)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo estado de conciliación.
     *
     * @param estadoConciliacionDTO DTO con los datos del estado de conciliación a crear
     * @return Estado de conciliación creado
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo estado de conciliación",
            description = "Crea un nuevo estado de conciliación con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estado de conciliación creado exitosamente",
                    content = @Content(schema = @Schema(implementation = EstadoConciliacionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<EstadoConciliacionDTO> createEstadoConciliacion(
            @Valid @RequestBody EstadoConciliacionDTO estadoConciliacionDTO) {

        // Verificar si ya existe un estado con ese código
        if (estadoConciliacionService.existsEstadoConciliacion(estadoConciliacionDTO.getCodigoEstado())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        EstadoConciliacion estadoConciliacion = estadoConciliacionDTOMapper.toEstadoConciliacion(estadoConciliacionDTO);
        EstadoConciliacion createdEstadoConciliacion = estadoConciliacionService.saveEstadoConciliacion(estadoConciliacion);
        EstadoConciliacionDTO createdDTO = estadoConciliacionDTOMapper.toEstadoConciliacionDTO(createdEstadoConciliacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }

    /**
     * Actualiza un estado de conciliación existente.
     *
     * @param codigoEstado Código del estado de conciliación a actualizar
     * @param estadoConciliacionDTO DTO con los nuevos datos
     * @return Estado de conciliación actualizado o 404 si no existe
     */
    @PutMapping("/{codigoEstado}")
    @Operation(summary = "Actualizar un estado de conciliación",
            description = "Actualiza un estado de conciliación según el código proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de conciliación actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = EstadoConciliacionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Estado de conciliación no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<EstadoConciliacionDTO> updateEstadoConciliacion(
            @PathVariable String codigoEstado,
            @Valid @RequestBody EstadoConciliacionDTO estadoConciliacionDTO) {

        // Asegurarse que el código en el path coincida con el del body
        if (!codigoEstado.equals(estadoConciliacionDTO.getCodigoEstado())) {
            estadoConciliacionDTO.setCodigoEstado(codigoEstado);
        }

        EstadoConciliacion estadoConciliacion = estadoConciliacionDTOMapper.toEstadoConciliacion(estadoConciliacionDTO);
        Optional<EstadoConciliacion> updatedEstadoConciliacion =
                estadoConciliacionService.updateEstadoConciliacion(codigoEstado, estadoConciliacion);

        return updatedEstadoConciliacion
                .map(estado -> ResponseEntity.ok(estadoConciliacionDTOMapper.toEstadoConciliacionDTO(estado)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un estado de conciliación por su código.
     *
     * @param codigoEstado Código del estado de conciliación a eliminar
     * @return 204 si se eliminó correctamente, 404 si no existe
     */
    @DeleteMapping("/{codigoEstado}")
    @Operation(summary = "Eliminar un estado de conciliación",
            description = "Elimina un estado de conciliación según el código proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estado de conciliación eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estado de conciliación no encontrado")
    })
    public ResponseEntity<Void> deleteEstadoConciliacion(@PathVariable String codigoEstado) {
        boolean deleted = estadoConciliacionService.deleteEstadoConciliacion(codigoEstado);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}