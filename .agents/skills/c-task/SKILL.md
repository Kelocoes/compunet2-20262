---
name: c-task
description: Descompone un plan técnico en tareas atómicas, verificables, independientes y potencialmente paralelizadas, guardadas en .agents/tasks/<nombre>-task.md con checklists de Markdown [ ] y criterios de prueba explícitos.
---

# Skill: /c-task (Tareas Atómicas Verificables)

Esta skill toma como insumo un plan técnico (`.agents/plans/<nombre>-plan.md`) y lo convierte en una secuencia estructurada de tareas atómicas, verificables e independientes listas para ejecución.

## Principios Fundamentales
1. **Atomicidad e Independencia**: Cada tarea debe abordar un entregable concreto de bajo acoplamiento (ej. script + entidad JPA, o DTOs + Mapper, o Servicio + Test unitario).
2. **Potencial de Paralelización**: Identificar claramente qué tareas son secuenciales por dependencia técnica y cuáles se pueden ejecutar en paralelo (ej. crear DTOs/Mappers en paralelo con la entidad de base de datos).
3. **Criterios de Prueba Ejecutables**: Ninguna tarea se considera completa sin una prueba explícita (ej. `./mvnw clean compile`, ejecución de test unitario con `./mvnw test -Dtest=...`, o llamada MockMvc).
4. **Checkboxes de Markdown**: Todas las tareas, acciones y verificaciones deben usar sintaxis interactiva `- [ ]` para que el agente o desarrollador pueda marcar con `- [x]` a medida que avanza.
5. **Convención de nomenclatura**: El archivo generado debe guardarse bajo:
   ```
   .agents/tasks/<nombre-funcionalidad>-task.md
   ```
   Ejemplo: `.agents/tasks/auth-user-task.md`, `.agents/tasks/routine-management-task.md`.
6. **Uso de plantilla**: Basado en [`.agents/templates/task-template.md`](../../templates/task-template.md).

---

## Flujo de Trabajo para /c-task

### 1. Análisis de Dependencias Técnicas
Evaluar el plan técnico y trazar el grafo de dependencias:
- **Capa Base**: Migración de base de datos, entidades JPA y repositorios.
- **Capa DTO & Mapeo**: DTOs y Mappers MapStruct (pueden paralelizarse con persistencia si los campos están pactados en la spec).
- **Capa de Negocio**: Servicios, transacciones y lógica con pruebas Mockito (depende de Repositorios y Mappers).
- **Capa Web & Seguridad**: Controladores REST y filtros de seguridad (depende de Servicios).

### 2. Estructuración de Tareas
Para cada tarea definida:
- Título descriptivo con checkbox: `### - [ ] Tarea N: [Nombre]`.
- Nivel de paralelismo o dependencias previas requeridas.
- Lista exhaustiva de archivos a crear o modificar:
  - Ruta exacta del archivo y resumen de cambios esperados en él.
- Acciones requeridas detalladas con checkboxes individuales:
  - `- [ ] 1. Crear clase ...`
  - `- [ ] 2. Configurar anotaciones ...`
- Criterios de prueba y verificación con checkboxes:
  - `- [ ] Comando ejecutable de verificación (ej. ./mvnw test -Dtest=...)`.
  - `- [ ] Validación esperada del resultado`.

### 3. Checklist Global de Progreso
Incluir al inicio del documento un resumen con checkboxes:
```markdown
## Resumen de Progreso
- [ ] Tarea 1: Modelo de Datos y Entidades
- [ ] Tarea 2: DTOs y Mappers (Paralelizable con Tarea 1)
- [ ] Tarea 3: Servicio de Negocio y Pruebas Unitarias
- [ ] Tarea 4: Controlador REST y Pruebas de Integración
```

### 4. Salida
Crear o actualizar `.agents/tasks/<nombre>-task.md` listo para que el agente ejecutor comience a marcar las casillas correspondientes con `[x]`.
