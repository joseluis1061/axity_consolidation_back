package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Documento;
import com.josedev.axity_consolidation_back.domain.service.DocumentoService;
import com.josedev.axity_consolidation_back.persistence.mapper.DocumentoMapper;
import com.josedev.axity_consolidation_back.persistence.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Documento> obtenerTodosLosDocumentos() {
        log.info("Obteniendo todos los documentos");
        return documentoMapper.entityListToModelList(documentoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Documento obtenerDocumentoPorCodigo(String codigoDocumento) {
        log.info("Obteniendo documento con código: {}", codigoDocumento);
        return documentoRepository.findById(codigoDocumento)
                .map(documentoMapper::entityToModel)
                .orElse(null);
    }
}