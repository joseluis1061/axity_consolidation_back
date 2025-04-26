package com.josedev.axity_consolidation_back.domain.service;

import com.josedev.axity_consolidation_back.domain.model.Documento;

import java.util.List;

public interface DocumentoService {

    /**
     * Obtiene todos los documentos
     * @return Lista de todos los documentos
     */
    List<Documento> obtenerTodosLosDocumentos();

    /**
     * Obtiene un documento por su código
     * @param codigoDocumento Código del documento a buscar
     * @return Documento encontrado o null si no existe
     */
    Documento obtenerDocumentoPorCodigo(String codigoDocumento);
}