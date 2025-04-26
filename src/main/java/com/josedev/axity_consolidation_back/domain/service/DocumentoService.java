package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Documento;

import java.util.List;
import java.util.Optional;

public interface DocumentoService {

    /**
     * Obtiene todos los documentos
     * @return Lista de todos los documentos
     */
    List<Documento> obtenerTodosLosDocumentos();

    /**
     * Obtiene un documento por su código
     * @param codigoDocumento Código del documento a buscar
     * @return Optional conteniendo el documento si existe
     */
    Optional<Documento> obtenerDocumentoPorCodigo(String codigoDocumento);

    /**
     * Guarda un documento (nuevo o existente)
     * @param documento Documento a guardar
     * @return Documento guardado
     * @throws IllegalArgumentException si el documento no cumple las validaciones
     */
    Documento guardarDocumento(Documento documento);

    /**
     * Elimina un documento por su código
     * @param codigoDocumento Código del documento a eliminar
     * @return true si el documento fue eliminado, false si no existía
     */
    boolean eliminarDocumento(String codigoDocumento);
}