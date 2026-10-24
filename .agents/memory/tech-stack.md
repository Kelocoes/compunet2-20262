# Stack Tecnológico Inmutable

Este documento constituye la verdad arquitectónica permanente del proyecto. Ningún agente debe sugerir dependencias obsoletas, versiones incompatibles o librerías alternativas sin autorización explícita.

## Lenguaje y Entorno de Ejecución
- Java: 17 LTS (configurado en `pom.xml` via property `<java.version>17</java.version>`)
- Gestor de dependencias y construcción: Apache Maven (`mvnw`, `mvnw.cmd`)

## Framework Base
- Spring Boot: 4.1.1 (definido en parent `org.springframework.boot:spring-boot-starter-parent:4.1.1`)
- Módulos Spring incluidos:
  - `spring-boot-starter-webmvc`: Controladores MVC y REST
  - `spring-boot-starter-data-jpa`: Persistencia ORM sobre Jakarta Persistence e Hibernate
  - `spring-boot-starter-security`: Autenticación, autorización y filtros de seguridad
  - `spring-boot-starter-thymeleaf`: Motor de plantillas SSR
  - `spring-boot-h2console`: Consola interactiva H2 para desarrollo

## Bases de Datos
- Motor en Producción / Contenedores: PostgreSQL (driver `org.postgresql:postgresql`, contenedor `postgres:16` via `docker-compose.yml`, puerto mapeado 5440 -> 5432)
- Motor en Desarrollo Local / Testing: H2 Database (`com.h2database:h2`, en memoria `jdbc:h2:mem:db-kevin;DB_CLOSE_DELAY=-1`)
- Inicialización DDL/DML:
  - `spring.jpa.hibernate.ddl-auto=create-drop` (entorno dev)
  - `spring.sql.init.mode=always` cargando `data.sql`
  - Estrategia recomendada a futuro: Migraciones incrementales con Flyway o Liquibase

## Librerías de Utilidad y Mapeo
- Lombok: Generación de getters, setters, constructores y builders (`org.projectlombok:lombok`)
- MapStruct: Mapeo de entidades a DTOs (`org.mapstruct:mapstruct:1.5.5.Final` con `mapstruct-processor` en compiler plugin)
- Seguridad JWT:
  - `io.jsonwebtoken:jjwt-api:0.12.3`
  - `io.jsonwebtoken:jjwt-impl:0.12.6`
  - `io.jsonwebtoken:jjwt-jackson:0.12.6`

## Pruebas y Testing
- JUnit 5 / Spring Test Starter:
  - `spring-boot-starter-data-jpa-test`
  - `spring-boot-starter-webmvc-test`
- Perfil de pruebas: `application-test.properties`

## Reglas para Agentes
1. No sugerir dependencias de Java EE (`javax.*`), usar siempre Jakarta EE (`jakarta.*`).
2. No sustituir MapStruct por ModelMapper ni reflexión dinámica.
3. No alterar las versiones de JJWT sin verificar compatibilidad de API (uso de parserBuilder / verifyWith).
4. No introducir drivers o clientes de bases de datos no soportadas.
