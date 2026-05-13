package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de negocio para la gestión de productos.
 *
 * NOTA DIDÁCTICA: Esta clase contiene intencionalmente problemas de calidad
 * para el análisis con SonarQube (Post-Contenido 1, Unidad 10).
 * Los problemas serán corregidos en el Post-Contenido 2.
 */
@Service
public class ProductoService {

    // Code Smell: inyección por campo con @Autowired (debería ser por constructor)
    // Code Smell: campo no final, no permite inmutabilidad ni facilita pruebas unitarias
    @Autowired
    private ProductoRepository repo; // Code Smell: nombre genérico poco descriptivo

    /**
     * Procesa y persiste un producto con las validaciones básicas.
     *
     * Code Smell: método largo con múltiples responsabilidades (Complejidad Ciclomática alta).
     * Code Smell: demasiados parámetros (más de 4 es un smell reconocido por SonarQube).
     * Code Smell: parámetros 'cat', 'activo' y 'proveedor' no se usan (dead parameters).
     */
    public Producto procesarProducto(String n, Double p, Integer s,
                                     String cat, boolean activo, String proveedor) {

        Producto producto = new Producto();

        // Code Smell: comparación con equals("") en lugar de isBlank() (Java 11+)
        if (n == null || n.equals("")) {
            throw new IllegalArgumentException("nombre requerido");
        }

        if (p == null) {
            throw new IllegalArgumentException("precio requerido");
        } else if (p <= 0) {
            throw new IllegalArgumentException("precio invalido");
        } else if (p > 999999) {
            throw new IllegalArgumentException("precio excesivo");
        }

        if (s == null || s < 0) {
            throw new IllegalArgumentException("stock invalido");
        }

        producto.setNombre(n);
        producto.setPrecio(p);
        producto.setStock(s);

        // TODO: implementar lógica de categoría y proveedor
        return repo.save(producto);
    }

    /**
     * Retorna todos los productos almacenados.
     */
    public List<Producto> listar() {
        return repo.findAll();
    }

    /**
     * Busca un producto por su identificador.
     *
     * Bug: retorna null cuando el producto no existe, en lugar de lanzar
     * una excepción apropiada (NoSuchElementException o ResponseStatusException).
     * Cualquier llamador que no verifique null obtendrá un NullPointerException.
     */
    public Producto buscar(Long id) {
        return repo.findById(id).orElse(null); // Bug: retorna null silenciosamente
    }

    /**
     * Elimina un producto por su identificador.
     *
     * Bug: no verifica si el producto existe antes de eliminarlo.
     * Si el id no existe, deleteById lanza EmptyResultDataAccessException
     * sin manejo, lo que expone detalles internos al cliente.
     */
    public void eliminar(Long id) {
        repo.deleteById(id); // Bug: sin verificación de existencia previa
    }
}
