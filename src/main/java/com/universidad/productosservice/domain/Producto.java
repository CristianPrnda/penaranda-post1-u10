package com.universidad.productosservice.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad JPA Producto.
 *
 * NOTA DIDÁCTICA: Esta clase contiene intencionalmente problemas de calidad
 * para el análisis con SonarQube (Post-Contenido 1, Unidad 10).
 * Los problemas serán corregidos en el Post-Contenido 2.
 */
@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;   // Bug: sin @Column(nullable=false) — permite nulos silenciosos

    private Double precio;

    private Integer stock;

    /**
     * Retorna el estado del stock del producto.
     *
     * Code Smell: lógica de negocio dentro de una entidad JPA (viola SRP).
     * Code Smell: complejidad ciclomática alta (7 ramas if-else encadenadas).
     */
    public String getEstado() {
        if (stock == null) return "DESCONOCIDO"; // Bug potencial: NullPointerException evitado, pero el campo debería ser not-null
        if (stock == 0) return "AGOTADO";
        if (stock > 0 && stock <= 5) return "BAJO";
        if (stock > 5 && stock <= 20) return "NORMAL";
        if (stock > 20 && stock <= 50) return "ALTO";
        if (stock > 50 && stock <= 100) return "MUY_ALTO";
        if (stock > 100) return "EXCEDENTE";
        return "DESCONOCIDO"; // Code Smell: rama inalcanzable (dead code)
    }
}
