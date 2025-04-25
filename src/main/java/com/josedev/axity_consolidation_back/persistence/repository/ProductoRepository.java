package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, String> {
    // Hereda los métodos básicos de JpaRepository
}
