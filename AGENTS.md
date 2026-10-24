# Guía de Agentes Autónomos (AGENTS.md)

Este documento es el punto de entrada raíz para cualquier agente de inteligencia artificial o desarrollador que interactúe con el repositorio. Define el marco operativo, las restricciones arquitectónicas y el flujo de trabajo guiado por la carpeta `.agents/`.

---

## Estructura del Sistema de Agentes

Toda la documentación operativa, memoria y especificaciones residen bajo la carpeta [`.agents/`](.agents/README.md):

```
compunet2-20262/
├── AGENTS.md               # Entrypoint raíz para agentes y desarrolladores
└── .agents/
    ├── README.md           # Índice y descripción detallada del sistema
    ├── memory/             # Verdad arquitectónica y tecnológica permanente
    │   ├── tech-stack.md   # Stack tecnológico inmutable (Java 17, Spring Boot, etc.)
    │   └── architecture.md # Diagramas de capas y modelo de datos
    ├── specs/              # El QUÉ: Requerimientos, JSON schemas y Gherkin (<nombre>-spec.md)
    ├── plans/              # El CÓMO: Clases, servicios, controladores y migraciones (<nombre>-plan.md)
    ├── tasks/              # Tareas atómicas con checkboxes [ ] y tests (<nombre>-task.md)
    ├── templates/          # Plantillas estándar para specs, plans y tasks
    │   ├── spec-template.md
    │   ├── plan-template.md
    │   └── task-template.md
    └── skills/             # Skills operativas para guiar el flujo de desarrollo
        ├── c-spec/SKILL.md # Skill /c-spec: Define especificaciones agnósticas a la tecnología
        ├── c-plan/SKILL.md # Skill /c-plan: Diseña el plan técnico y componentes sin código prematuro
        └── c-task/SKILL.md # Skill /c-task: Descompone el plan en tareas verificables con checkboxes
```

---

## Flujo de Trabajo Obligatorio y Skills Asociadas

Cualquier cambio o adición al código debe cumplir rigurosamente el siguiente ciclo de 4 pasos antes de modificar código de producción:

```mermaid
flowchart LR
    A[Consultar .agents/memory] --> B["Skill /c-spec (.agents/specs/<nombre>-spec.md)"]
    B --> C["Skill /c-plan (.agents/plans/<nombre>-plan.md)"]
    C --> D["Skill /c-task (.agents/tasks/<nombre>-task.md)"]
    D --> E[Ejecución y marcado [x] con tests]
```

### 1. Memoria Arquitectónica (.agents/memory/)
- Antes de proponer soluciones o librerías, leer [.agents/memory/tech-stack.md](.agents/memory/tech-stack.md) y [.agents/memory/architecture.md](.agents/memory/architecture.md).
- Respetar el stack inmutable: Java 17, Spring Boot, PostgreSQL / H2, MapStruct, Lombok y JJWT.
- No sugerir dependencias obsoletas ni paquetes `javax.*`.

### 2. Especificación con Skill `/c-spec` (.agents/specs/)
- Responder al **QUÉ** de forma **agnóstica a la tecnología**.
- Guardar el archivo como `.agents/specs/<nombre>-spec.md`.
- Usar la plantilla [.agents/templates/spec-template.md](.agents/templates/spec-template.md).
- Incluir historias de usuario, esquemas JSON de petición/respuesta y criterios de aceptación en formato Gherkin.

### 3. Plan Técnico con Skill `/c-plan` (.agents/plans/)
- Responder al **CÓMO** a nivel de arquitectura y diseño sin redactar código fuente de producción.
- Guardar el archivo como `.agents/plans/<nombre>-plan.md`.
- Usar la plantilla [.agents/templates/plan-template.md](.agents/templates/plan-template.md).
- Identificar clases (`@RestController`, `@Service`, `@Entity`, `@Repository`), DTOs, mappers y diagramas de secuencia Mermaid.

### 4. Tareas Verificables con Skill `/c-task` (.agents/tasks/)
- Dividir el plan en tareas pequeñas, independientes y potencialmente paralelizadas.
- Guardar el archivo como `.agents/tasks/<nombre>-task.md`.
- Usar la plantilla [.agents/templates/task-template.md](.agents/templates/task-template.md) con checkboxes (`- [ ]`) en tareas, acciones y criterios de prueba.
- El agente ejecutor debe marcar con `- [x]` cada paso una vez verificado mediante compilación (`./mvnw clean compile`) o pruebas automáticas (`./mvnw test`).

---

## Reglas de Interacción para Agentes
1. No modificar código de producción directamente sin tener una tarea y especificación de respaldo.
2. Mantener la integridad de los paquetes existentes bajo `com.example.teacher`.
3. No utilizar emojis en la documentación técnica ni en commits generados.
4. Usar diagramas Mermaid claros para flujos o relaciones arquitectónicas complejas.
