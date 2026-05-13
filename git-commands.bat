:: ============================================================
:: SCRIPT DE INICIALIZACIÓN DE GIT - Post-Contenido 1 Unidad 10
:: Ejecutar en la carpeta raíz del proyecto (penaranda-post1-u10)
:: ============================================================

:: 1. Inicializar el repositorio local
git init

:: 2. Configurar identidad (si no está configurada globalmente)
git config user.name "CristianPrnda"
git config user.email "TU_CORREO@ejemplo.com"

:: 3. COMMIT 1 — Setup inicial del proyecto
git add pom.xml .gitignore src/main/resources/application.properties
git add src/main/java/com/universidad/productosservice/ProductosServiceApplication.java
git commit -m "feat: setup inicial del proyecto Spring Boot con dependencias H2, JPA y Lombok"

:: 4. COMMIT 2 — Código con problemas de calidad
git add src/main/java/com/universidad/productosservice/domain/Producto.java
git add src/main/java/com/universidad/productosservice/repository/ProductoRepository.java
git add src/main/java/com/universidad/productosservice/service/ProductoService.java
git add src/main/java/com/universidad/productosservice/controller/ProductoController.java
git add src/test/java/com/universidad/productosservice/ProductosServiceApplicationTests.java
git commit -m "feat: agregar codigo con problemas de calidad intencionados para analisis SonarQube"

:: 5. COMMIT 3 — Configuración JaCoCo y SonarQube
git add sonar-project.properties
git commit -m "chore: configurar plugin JaCoCo en pom.xml y agregar sonar-project.properties"

:: 6. Conectar con GitHub y subir
:: Reemplaza TU_USUARIO con tu usuario de GitHub si es diferente
git remote add origin https://github.com/CristianPrnda/penaranda-post1-u10.git
git branch -M main
git push -u origin main

:: 7. Después de ejecutar el análisis con SonarQube y tomar capturas,
::    agregar las capturas a la carpeta docs/ y hacer el commit final:
::
:: git add docs/
:: git add README.md
:: git commit -m "docs: documentar hallazgos del analisis SonarQube con capturas del dashboard"
:: git push
