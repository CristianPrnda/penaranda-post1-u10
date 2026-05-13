package com.universidad.productosservice.controller;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el recurso Producto.
 *
 * NOTA DIDÁCTICA: Contiene code smells menores para enriquecer el análisis de SonarQube.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Code Smell: inyección por campo (misma situación que en el servicio)
    @Autowired
    private ProductoService productoService;

    /**
     * GET /api/productos — lista todos los productos.
     */
    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    /**
     * GET /api/productos/{id} — obtiene un producto por id.
     *
     * Bug: si buscar() retorna null, ResponseEntity.ok(null) devuelve HTTP 200 con cuerpo vacío
     * en lugar de HTTP 404. El cliente no puede distinguir "no encontrado" de "vacío".
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        Producto p = productoService.buscar(id);
        return ResponseEntity.ok(p); // Bug: debería ser 404 si p es null
    }

    /**
     * POST /api/productos — crea un nuevo producto.
     *
     * Code Smell: los parámetros de negocio (cat, activo, proveedor)
     * se pasan con valores hardcodeados/por defecto en lugar de
     * recibir un DTO apropiado.
     */
    @PostMapping
    public Producto crear(@RequestBody Producto request) {
        return productoService.procesarProducto(
                request.getNombre(),
                request.getPrecio(),
                request.getStock(),
                "GENERAL",   // Code Smell: valor hardcodeado
                true,        // Code Smell: valor hardcodeado
                "N/A"        // Code Smell: valor hardcodeado
        );
    }

    /**
     * DELETE /api/productos/{id} — elimina un producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
