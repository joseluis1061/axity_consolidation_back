package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.EstadoConciliacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoConciliacionRepository extends JpaRepository<EstadoConciliacionEntity, String> {
    // Hereda los métodos básicos de JpaRepository
}
