# SITAF Backend Principal (Spring Boot)

Backend principal del sistema SITAF desarrollado en Java con Spring Boot. Este componente actúa como la API principal que interactúa tanto con la Base de Datos Relacional (PostgreSQL), el modelo de IA en Python y la red de Hyperledger Fabric (Blockchain).

## Requisitos Previos
- **Java 21**
- **Maven**
- **PostgreSQL** instalado y corriendo en el puerto 5432 (o usar H2 en memoria según esté configurado en `application.yml`).
- La red de **Hyperledger Fabric** debe estar ejecutándose para el Módulo 2 de Trazabilidad.
- El **Backend de IA (Python)** debe estar corriendo en el puerto 8000 para el Módulo 3.

## Configuración y Base de Datos

El backend está configurado para inicializar automáticamente las tablas. Además, `DataSeeder.java` inserta registros iniciales (medicamentos) en la base de datos automáticamente al arrancar, para que se puedan simular los datos base del SIGA.

Si la base de datos es PostgreSQL (configúralo en `application.yml`):
- Usuario por defecto: (Ver `application.yml`)
- Contraseña por defecto: (Ver `application.yml`)

### Certificados Blockchain (IMPORTANTE)
Si la red de Blockchain se reinicia, los certificados en `fabric-samples/test-network/organizations` cambiarán de nombre. Debes actualizar el path del certificado `.sk` del administrador en el archivo `src/main/resources/application.yml`.

## Instalación y Ejecución

1. Clona el repositorio.
2. Navega al directorio raíz del proyecto.
3. Ejecuta el siguiente comando para levantar el servidor:
   ```bash
   ./mvnw spring-boot:run
   ```
El backend estará disponible en `http://localhost:8080`.

## Endpoints Principales
- `/api/trazabilidad/lote/{lote}`: Consulta el historial de un lote en la Blockchain.
- `/api/stock/prediccion/{codigo}`: Obtiene predicciones de la IA para un medicamento.
- `/api/stock/alertas/caducidad`: Obtiene las alertas preventivas de medicamentos cercanos a vencer.
