package com.universidad.productosservice.repository;

import com.universidad.productosservice.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Producto.
 * Extiende JpaRepository para operaciones CRUD básicas.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Spring Data JPA genera la implementación automáticamente
}
