package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.DocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, String> {
    // Hereda los métodos básicos de JpaRepository
}