# [TASK-001] Lista de Tareas Verificables: Pantalla MVC de Roles

## Metadatos
- ID: TASK-001
- Plan Asociado: [PLAN-001](../plans/roles-mvc-plan.md)
- Spec Asociada: [SPEC-001](../specs/roles-mvc-spec.md)
- Fecha: 2026-10-24
- Estado General: NOT_STARTED

## Resumen de Progreso
- [x] Tarea 1: Creación de Plantillas Thymeleaf y Estilos CSS para Roles
- [x] Tarea 2: Implementación de RoleMVCController y Mapeo de Rutas
- [x] Tarea 3: Verificación con Pruebas Unitarias / WebMvcTest de RoleMVCController
- [x] Tarea 4: Verificación Integral de Compilación y Suite de Pruebas

---

## Desglose Detallado de Tareas

### - [x] Tarea 1: Creación de Plantillas Thymeleaf y Estilos CSS para Roles
- Responsable: Agente / Desarrollador
- Dependencias previas: Ninguna (paralelizable)
- Archivos involucrados:
  - `src/main/resources/templates/roles/list.html`
  - `src/main/resources/templates/roles/add.html`
  - `src/main/resources/templates/roles/edit.html`
  - `src/main/resources/static/css/roles/list.css`
  - `src/main/resources/static/css/roles/add.css`
- Acciones requeridas:
  - [x] 1. Crear `roles/list.html` incluyendo el fragmento `components/sidebar :: sidebar`, la tabla con columnas `ID`, `Name`, enlaces `Edit`, `Delete`, y botón `Add Role`.
  - [x] 2. Crear `roles/add.html` con formulario `POST` vinculado al objeto `role` con campo `name` y botón de guardar.
  - [x] 3. Crear `roles/edit.html` con formulario vinculado al objeto `actualRole` para actualizar `name`.
  - [x] 4. Crear archivos CSS `list.css` y `add.css` bajo `static/css/roles/` manteniendo la estética uniforme con el módulo de usuarios.
- Criterio de Prueba y Verificación:
  - [x] Las plantillas y recursos estáticos existen en las rutas correctas y tienen sintaxis Thymeleaf válida.

---

### - [x] Tarea 2: Implementación de RoleMVCController y Mapeo de Rutas
- Responsable: Agente / Desarrollador
- Dependencias previas: Tarea 1
- Archivos involucrados:
  - `src/main/java/com/example/teacher/controller/mvc/RoleMVCController.java`
- Acciones requeridas:
  - [x] 1. Crear la clase `RoleMVCController` anotada con `@Controller`, `@RequestMapping("/mvc/roles")`, `@RequiredArgsConstructor` y `@Slf4j`.
  - [x] 2. Inyectar `IRoleService`.
  - [x] 3. Implementar método `GET /mvc/roles` para listar roles y renderizar `roles/list`.
  - [x] 4. Implementar método `GET /mvc/roles/add` para preparar modelo y renderizar `roles/add`.
  - [x] 5. Implementar método `POST /mvc/roles/add` para persistir rol vía `roleService.create(role)` y redirigir a `/mvc/roles`.
  - [x] 6. Implementar método `GET /mvc/roles/edit/{id}` para cargar rol existente y renderizar `roles/edit`.
  - [x] 7. Implementar método `POST /mvc/roles/edit/{id}` (o `PUT`) para actualizar rol vía `roleService.update(id, role)` y redirigir a `/mvc/roles`.
  - [x] 8. Implementar método `GET /mvc/roles/delete/{id}` (o `DELETE`) para remover rol vía `roleService.deleteById(id)` y redirigir a `/mvc/roles`.
- Criterio de Prueba y Verificación:
  - [x] Compilación exitosa del proyecto con `./mvnw clean compile`.

---

### - [x] Tarea 3: Verificación con Pruebas Unitarias / WebMvcTest de RoleMVCController
- Responsable: Agente / Desarrollador
- Dependencias previas: Tarea 2
- Archivos involucrados:
  - `src/test/java/com/example/teacher/controller/mvc/RoleMVCControllerTest.java`
- Acciones requeridas:
  - [x] 1. Crear clase de prueba usando `@WebMvcTest(RoleMVCController.class)` o MockMvc independiente.
  - [x] 2. Mockear `IRoleService` con `@MockitoBean` (o `@MockBean`).
  - [x] 3. Probar escenario `GET /mvc/roles` esperando status 200, modelo con atributo `roles` y nombre de vista `roles/list`.
  - [x] 4. Probar escenario `POST /mvc/roles/add` esperando redirección `302 Found` a `/mvc/roles`.
  - [x] 5. Probar escenario `GET /mvc/roles/edit/{id}` esperando status 200 y modelo con `actualRole`.
  - [x] 6. Probar escenario `POST /mvc/roles/edit/{id}` esperando redirección `302 Found`.
  - [x] 7. Probar escenario `GET /mvc/roles/delete/{id}` esperando redirección `302 Found`.
- Criterio de Prueba y Verificación:
  - [x] Ejecución de la suite específica pasando en verde: `./mvnw test -Dtest=RoleMVCControllerTest`.

---

### - [x] Tarea 4: Verificación Integral de Compilación y Suite de Pruebas
- Responsable: Agente / Desarrollador
- Dependencias previas: Tarea 1, 2 y 3
- Archivos involucrados:
  - Proyecto completo
- Acciones requeridas:
  - [x] 1. Ejecutar `./mvnw clean test` para certificar que ningún cambio alteró los tests existentes ni la configuración de seguridad.
  - [x] 2. Marcar los checkboxes correspondientes en `[TASK-001]` a medida que se completen las verificaciones.
- Criterio de Prueba y Verificación:
  - [x] Build exitoso reportando `BUILD SUCCESS`.
