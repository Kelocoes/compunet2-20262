---
name: c-plan
description: Diseña planes técnicos de arquitectura y componentes para una especificación dada, guardados en .agents/plans/<nombre>-plan.md, definiendo el CÓMO técnico (clases, repositorios, controladores, DTOs y migraciones) sin escribir código de producción.
---

# Skill: /c-plan (Plan Técnico de Implementación)

Esta skill toma como insumo una especificación existente (`.agents/specs/<nombre>-spec.md`) y la memoria del proyecto (`.agents/memory/`) para estructurar el **CÓMO** técnico del desarrollo.

## Principios Fundamentales
1. **Sin código de producción prematuro**: El plan diseña la arquitectura, interfaces, clases y flujos, pero no redacta el código final de los métodos.
2. **Respeto a la verdad inmutable**: Debe apegarse estrictamente a lo establecido en [`.agents/memory/tech-stack.md`](../../memory/tech-stack.md) y [`.agents/memory/architecture.md`](../../memory/architecture.md) (Java 17, Spring Boot, MapStruct, Lombok, JPA/Hibernate, PostgreSQL/H2, JJWT).
3. **Convención de nomenclatura**: El archivo generado debe guardarse bajo:
   ```
   .agents/plans/<nombre-funcionalidad>-plan.md
   ```
   Ejemplo: `.agents/plans/auth-user-plan.md`, `.agents/plans/routine-management-plan.md`.
4. **Uso de plantilla**: Basado en [`.agents/templates/plan-template.md`](../../templates/plan-template.md).
5. **Cero emojis**: Uso de diagramas Mermaid estándar para diagramas de secuencia o arquitectura.

---

## Flujo de Trabajo para /c-plan

### 1. Insumos y Verificación Previa
- Leer la especificación asociada: `.agents/specs/<nombre>-spec.md`.
- Leer los documentos de `.agents/memory/` para constatar tecnologías y modelos existentes.

### 2. Identificación de Componentes por Capas
Detallar explícitamente qué componentes se crean o modifican:

#### A. Persistencia y Base de Datos
- Tablas a crear o alterar (columnas, tipos SQL, claves primarias y foráneas).
- Scripts SQL o migraciones necesarias (`src/main/resources/data.sql` o Flyway).
- Entidades JPA a intervenir o crear en `com.example.teacher.model` (anotaciones `@Entity`, `@Table`, relaciones `@ManyToOne`, etc.).
- Repositorios en `com.example.teacher.repository` extendiendo `JpaRepository`.

#### B. Capa de Mapeo y DTOs
- DTOs de Petición y Respuesta en `com.example.teacher.dto` con validaciones Jakarta (`@NotBlank`, `@Positive`, etc.).
- Mappers MapStruct en `com.example.teacher.mapper` con `@Mapper(componentModel = "spring")`.

#### C. Lógica de Negocio y Transaccionalidad
- Servicios en `com.example.teacher.service` (interfaces e implementaciones con `@Service`).
- Métodos que requieren `@Transactional` de lectura o escritura.
- Validaciones de negocio y excepciones personalizadas de dominio.

#### D. Exposición de Endpoints y Seguridad
- Controladores en `com.example.teacher.controller.rest` con `@RestController` y mapeo de rutas.
- Configuración de autorización en `WebSecurityConfig` o reglas vía `@PreAuthorize`.

### 3. Diagrama de Secuencia Técnico
Generar un diagrama Mermaid de secuencia (`sequenceDiagram`) que evidencie el flujo entre Cliente, Controller, Service, Mapper, Repository y Base de Datos.

### 4. Salida
Crear o actualizar `.agents/plans/<nombre>-plan.md` y presentarlo para revisión previa a la descomposición de tareas (`/c-task`).
