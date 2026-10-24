# Arquitectura y Diseño del Sistema

Documento canónico que define la estructura de capas, componentes y el modelo relacional del proyecto.

## Estructura de Capas de la Aplicación

```mermaid
graph TD
    Client[Cliente HTTP / Navegador / Postman] --> Security[Filtro WebSecurityConfig / JWT Auth Filter]
    Security --> Controllers{Enrutador de Controladores}
    
    Controllers -->|Vistas Web SSR| MVCControllers[com.example.teacher.controller.mvc]
    Controllers -->|API JSON / REST| RESTControllers[com.example.teacher.controller.rest]
    
    MVCControllers --> Thymeleaf[Plantillas Thymeleaf / HTML]
    MVCControllers --> Services[Capa de Servicios @Service]
    RESTControllers --> Services
    
    Services --> Mappers[Mapeadores MapStruct]
    Services --> Repositories[Repositorios Spring Data JPA @Repository]
    
    Repositories --> Database[(Base de Datos: PostgreSQL / H2)]
```

## Convención de Paquetes

- `com.example.teacher.config`: Configuraciones globales de Spring Security, beans de seguridad y CORS.
- `com.example.teacher.controller.mvc`: Controladores que renderizan vistas Thymeleaf (`UserMVCController`, `AuthMVCController`, etc.).
- `com.example.teacher.controller.rest`: Controladores REST con `@RestController` que entregan respuestas JSON y códigos de estado semánticos (`AuthRestController`, `UserController`, etc.).
- `com.example.teacher.dto`: Objetos de transferencia de datos clasificados en requests y responses.
- `com.example.teacher.exception` y `RestExceptionHandler`: Manejo centralizado de errores vía `@RestControllerAdvice`.
- `com.example.teacher.mapper`: Interfaces de mapeo MapStruct entre Entidades JPA y DTOs.
- `com.example.teacher.model`: Entidades JPA anotadas con Jakarta Persistence (`@Entity`, `@Table`).
- `com.example.teacher.repository`: Interfaces que extienden `JpaRepository` o `CrudRepository`.
- `com.example.teacher.service`: Lógica de negocio transaccional (`@Service`).

## Modelo de Dominio y Entidades

```mermaid
erDiagram
    USERS ||--o{ USER_ROLES : has
    ROLES ||--o{ USER_ROLES : assigned_to
    ROLES ||--o{ ROLE_PERMISSIONS : contains
    PERMISSIONS ||--o{ ROLE_PERMISSIONS : defined_in
    
    USERS ||--o{ ACTIVITY_LOGS : performs
    ROUTINES ||--o{ ROUTINE_EXERCISES : includes
    EXERCISES ||--o{ ROUTINE_EXERCISES : part_of
    ACTIVITY_LOGS ||--o{ ACTIVITY_EXERCISES : logs
    EXERCISES ||--o{ ACTIVITY_EXERCISES : references

    USERS {
        bigint id PK
        varchar username
        varchar password
        varchar email
    }
    ROLES {
        bigint id PK
        varchar name
        varchar description
    }
    PERMISSIONS {
        bigint id PK
        varchar name
        varchar description
    }
    ROUTINES {
        bigint id PK
        varchar name
        varchar description
    }
    EXERCISES {
        bigint id PK
        varchar name
        varchar muscle_group
    }
    ACTIVITY_LOGS {
        bigint id PK
        bigint user_id FK
        timestamp created_at
    }
```

## Reglas Arquitectónicas Obligatorias
1. Los controladores no deben acceder directamente a la capa de persistencia (Repositories); toda invocación debe canalizarse a través de la capa de Servicios (`@Service`).
2. Las entidades JPA nunca deben exponerse directamente en endpoints REST ni recibirse como payload crudo; deben mapearse obligatoriamente con DTOs y MapStruct.
3. El manejo de transacciones de escritura debe declararse a nivel de servicio con `@Transactional`.
4. Los endpoints REST deben respetar el context-path global configurado (`/compunet2-2026`).
