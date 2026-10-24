# [TASK-XXX] Lista de Tareas Verificables

## Metadatos
- ID: TASK-XXX
- Plan Asociado: [PLAN-XXX](../plans/PLAN-XXX.md)
- Spec Asociada: [SPEC-XXX](../specs/SPEC-XXX.md)
- Fecha: YYYY-MM-DD
- Estado General: [NOT_STARTED | IN_PROGRESS | DONE]

## Resumen de Progreso
- [ ] Tarea 1: [Nombre de la tarea 1]
- [ ] Tarea 2: [Nombre de la tarea 2]
- [ ] Tarea 3: [Nombre de la tarea 3]
- [ ] Tarea 4: [Nombre de la tarea 4]

---

## Desglose Detallado de Tareas

### - [ ] Tarea 1: [Nombre de la tarea - Ej. Modelo de Datos y Migración]
- Responsable: [Agente / Desarrollador]
- Dependencias previas: Ninguna
- Archivos involucrados:
  - `src/main/resources/data.sql`
  - `src/main/java/com/example/teacher/model/NombreEntidad.java`
  - `src/main/java/com/example/teacher/repository/NombreRepository.java`
- Acciones requeridas:
  - [ ] 1. Definir script SQL con constraints y tipos de columna adecuados.
  - [ ] 2. Crear la clase `@Entity` con anotaciones Jakarta Persistence y Lombok.
  - [ ] 3. Crear interface `@Repository` extendiendo `JpaRepository`.
- Criterio de Prueba y Verificación:
  - [ ] La aplicación compila sin errores (`./mvnw clean compile`).
  - [ ] La tabla se genera y los scripts iniciales se ejecutan correctamente en H2/PostgreSQL al levantar el contexto.

---

### - [ ] Tarea 2: [Nombre de la tarea - Ej. DTOs y Mapeadores MapStruct]
- Responsable: [Agente / Desarrollador]
- Dependencias previas: Tarea 1
- Archivos involucrados:
  - `src/main/java/com/example/teacher/dto/request/NombreRequestDTO.java`
  - `src/main/java/com/example/teacher/dto/response/NombreResponseDTO.java`
  - `src/main/java/com/example/teacher/mapper/NombreMapper.java`
- Acciones requeridas:
  - [ ] 1. Crear DTOs con anotaciones de validación (`@NotBlank`, `@NotNull`).
  - [ ] 2. Crear mapper MapStruct con `@Mapper(componentModel = "spring")`.
- Criterio de Prueba y Verificación:
  - [ ] Compilación exitosa del procesador de anotaciones de MapStruct (`target/generated-sources`).

---

### - [ ] Tarea 3: [Nombre de la tarea - Ej. Servicio y Lógica de Negocio]
- Responsable: [Agente / Desarrollador]
- Dependencias previas: Tarea 2
- Archivos involucrados:
  - `src/main/java/com/example/teacher/service/NombreService.java`
  - `src/main/java/com/example/teacher/service/impl/NombreServiceImpl.java`
  - `src/test/java/com/example/teacher/service/NombreServiceTest.java`
- Acciones requeridas:
  - [ ] 1. Declarar métodos en la interfaz de servicio.
  - [ ] 2. Implementar servicio anotado con `@Service` y `@Transactional`.
  - [ ] 3. Escribir pruebas unitarias con Mockito.
- Criterio de Prueba y Verificación:
  - [ ] Ejecución de pruebas unitarias pasando en verde (`./mvnw test -Dtest=NombreServiceTest`).

---

### - [ ] Tarea 4: [Nombre de la tarea - Ej. Controlador REST y Exposición de Endpoints]
- Responsable: [Agente / Desarrollador]
- Dependencias previas: Tarea 3
- Archivos involucrados:
  - `src/main/java/com/example/teacher/controller/rest/NombreRestController.java`
  - `src/main/java/com/example/teacher/config/WebSecurityConfig.java`
  - `src/test/java/com/example/teacher/controller/rest/NombreRestControllerTest.java`
- Acciones requeridas:
  - [ ] 1. Implementar `@RestController` con `@RequestMapping`.
  - [ ] 2. Configurar reglas de seguridad en `WebSecurityConfig` o anotaciones `@PreAuthorize`.
  - [ ] 3. Escribir prueba de integración con `@WebMvcTest` o `MockMvc`.
- Criterio de Prueba y Verificación:
  - [ ] Ejecución de peticiones HTTP simuladas que verifiquen status codes 200/201 y 400/401 según los criterios Gherkin de la spec.
