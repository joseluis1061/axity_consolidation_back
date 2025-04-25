package com.josedev.axity_consolidation_back.persistence.repository;

import com.josedev.axity_consolidation_back.persistence.entity.ConciliacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConciliacionRepository extends JpaRepository<ConciliacionEntity, Long> {

    /**
     * Busca conciliaciones por estado
     * @param codigoEstado Código del estado a buscar
     * @return Lista de conciliaciones con el estado especificado
     */
    @Query("SELECT c FROM ConciliacionEntity c WHERE c.estadoConciliacion.codigoEstado = :codigoEstado")
    List<ConciliacionEntity> findByEstado(@Param("codigoEstado") String codigoEstado);

    /**
     * Busca conciliaciones con filtros dinámicos
     * Si un parámetro es null, no se aplica el filtro correspondiente
     */
    @Query("SELECT c FROM ConciliacionEntity c WHERE " +
            "(:fechaInicio IS NULL OR c.fechaConciliacion >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR c.fechaConciliacion <= :fechaFin) AND " +
            "(:codigoSucursal IS NULL OR c.sucursalProducto.sucursal.codigoSucursal = :codigoSucursal) AND " +
            "(:codigoProducto IS NULL OR c.sucursalProducto.producto.codigoProducto = :codigoProducto) AND " +
            "(:codigoEstado IS NULL OR c.estadoConciliacion.codigoEstado = :codigoEstado)")
    List<ConciliacionEntity> findByFiltros(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("codigoSucursal") String codigoSucursal,
            @Param("codigoProducto") String codigoProducto,
            @Param("codigoEstado") String codigoEstado);

    /**
     * Busca conciliaciones por año y mes (para proceso batch - API1)
     */
    @Query("SELECT c FROM ConciliacionEntity c WHERE " +
            "YEAR(c.fechaConciliacion) = :year AND " +
            "MONTH(c.fechaConciliacion) = :month")
    List<ConciliacionEntity> findByYearAndMonth(
            @Param("year") int year,
            @Param("month") int month);
}