package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoEntity;
import com.josedev.axity_consolidation_back.persistence.entity.SucursalProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalProductoRepository extends JpaRepository<SucursalProductoEntity, SucursalProductoId> {
    // Para esta entidad compuesta, usamos SucursalProductoId como tipo de clave primaria
}