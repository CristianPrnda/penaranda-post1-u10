package com.universidad.productosservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Prueba de arranque del contexto de Spring.
 *
 * NOTA DIDÁCTICA: La cobertura de pruebas es intencionalmente baja en este laboratorio.
 * Solo se verifica que el contexto de Spring cargue correctamente.
 * El bajo porcentaje de cobertura será uno de los hallazgos documentados en el análisis
 * de SonarQube. La cobertura completa se implementa en el Post-Contenido 2.
 */
@SpringBootTest
class ProductosServiceApplicationTests {

    @Test
    void contextLoads() {
        // El contexto de Spring Boot debe iniciar sin errores
    }

}
