package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define el repositorio para la entidad EstadoConciliacionEntity.
 * Extiende de JpaRepository para heredar métodos CRUD básicos.
 */
@Repository
public interface EstadoConciliacionRepository extends JpaRepository<EstadoConciliacionEntity, String> {

    /**
     * El repositorio utiliza String como tipo para la clave primaria (codigoEstado)
     * No se requieren métodos adicionales ya que JpaRepository proporciona:
     * - findAll(): Obtener todos los estados de conciliación
     * - findById(String id): Buscar un estado de conciliación por su código
     * - save(EstadoConciliacionEntity estadoConciliacion): Guardar o actualizar un estado de conciliación
     * - deleteById(String id): Eliminar un estado de conciliación por su código
     * - existsById(String id): Verificar si existe un estado de conciliación por su código
     */
}