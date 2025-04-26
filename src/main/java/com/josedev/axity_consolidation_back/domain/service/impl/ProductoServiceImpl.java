package com.josedev.axity_consolidation_back.domain.service.impl;

import com.josedev.axity_consolidation_back.domain.model.Producto;
import com.josedev.axity_consolidation_back.domain.service.ProductoService;
import com.josedev.axity_consolidation_back.persistence.entity.ProductoEntity;
import com.josedev.axity_consolidation_back.persistence.mapper.ProductoMapper;
import com.josedev.axity_consolidation_back.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Producto> obtenerProductoPorCodigo(String codigoProducto) {
        log.info("Obteniendo producto con código: {}", codigoProducto);

        if (codigoProducto == null || codigoProducto.isEmpty()) {
            throw new IllegalArgumentException("El código de producto no puede ser nulo o vacío");
        }

        return productoRepository.findById(codigoProducto)
                .map(productoMapper::entityToModel);
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        log.info("Guardando producto: {}", producto);

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        if (producto.getCodigoProducto() == null || producto.getCodigoProducto().isEmpty()) {
            throw new IllegalArgumentException("El código de producto no puede ser nulo o vacío");
        }

        if (producto.getNombreProducto() == null || producto.getNombreProducto().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }

        ProductoEntity entidad = productoMapper.modelToEntity(producto);
        ProductoEntity guardado = productoRepository.save(entidad);

        log.info("Producto guardado con código: {}", guardado.getCodigoProducto());
        return productoMapper.entityToModel(guardado);
    }

    @Override
    @Transactional
    public boolean eliminarProducto(String codigoProducto) {
        log.info("Eliminando producto con código: {}", codigoProducto);

        if (codigoProducto == null || codigoProducto.isEmpty()) {
            throw new IllegalArgumentException("El código de producto no puede ser nulo o vacío");
        }

        if (!productoRepository.existsById(codigoProducto)) {
            log.warn("No se encontró producto con código: {}", codigoProducto);
            return false;
        }

        try {
            productoRepository.deleteById(codigoProducto);
            log.info("Producto eliminado correctamente");
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar producto: {}", e.getMessage());
            throw new IllegalStateException("No se puede eliminar el producto porque está siendo utilizado", e);
        }
    }
}