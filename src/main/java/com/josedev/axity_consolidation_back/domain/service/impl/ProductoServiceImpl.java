package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Producto;
import com.josedev.axity_consolidation_back.domain.service.ProductoService;
import com.josedev.axity_consolidation_back.persistence.mapper.ProductoMapper;
import com.josedev.axity_consolidation_back.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerTodosLosProductos() {
        log.info("Obteniendo todos los productos");
        return productoMapper.entityListToModelList(productoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorCodigo(String codigoProducto) {
        log.info("Obteniendo producto con código: {}", codigoProducto);
        return productoRepository.findById(codigoProducto)
                .map(productoMapper::entityToModel)
                .orElse(null);
    }
}