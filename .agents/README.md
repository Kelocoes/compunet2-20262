# Sistema de Agentes Autónomos (.agents)

Este directorio constituye el entrypoint y la fuente de verdad estructurada para todos los agentes de inteligencia artificial y desarrolladores que interactúen con el repositorio.

## Estructura de Directorios

```
.agents/
├── README.md               # Entrypoint e índice general del sistema de agentes
├── memory/                 # Memoria inmutable y verdad arquitectónica del proyecto
│   ├── tech-stack.md       # Stack de tecnologías, dependencias y versiones inmutables
│   └── architecture.md     # Estructura de capas, convenciones y modelo de entidades
├── specs/                  # El QUÉ: especificaciones funcionales agnósticas (<nombre>-spec.md)
├── plans/                  # El CÓMO: diseño técnico de componentes y secuencia (<nombre>-plan.md)
├── tasks/                  # Tareas atómicas verificables con checkboxes [ ] (<nombre>-task.md)
├── templates/              # Plantillas estandarizadas para specs, plans y tasks
│   ├── spec-template.md    # Plantilla para especificaciones funcionales
│   ├── plan-template.md    # Plantilla para planes técnicos
│   └── task-template.md    # Plantilla para desglose de tareas ejecutables con checkboxes
└── skills/                 # Skills para guiar y automatizar el ciclo de desarrollo
    ├── c-spec/SKILL.md     # Skill /c-spec: Levantamiento agnóstico del QUÉ
    ├── c-plan/SKILL.md     # Skill /c-plan: Planificación técnica del CÓMO
    └── c-task/SKILL.md     # Skill /c-task: Desglose atómico, paralelización y verificación
```

## Flujo de Trabajo y Skills

Cualquier cambio de software o nueva funcionalidad debe seguir obligatoriamente las siguientes 4 fases secuenciales:

```mermaid
flowchart LR
    M[0. Memory] --> S["1. /c-spec"]
    S --> P["2. /c-plan"]
    P --> T["3. /c-task"]
    T --> C[4. Code & Checkboxes]
```

### 1. Fase de Memoria (Verificación Inmutable)
- Consultar [memory/tech-stack.md](memory/tech-stack.md) y [memory/architecture.md](memory/architecture.md).
- Ningún agente puede proponer librerías incompatibles, versiones obsoletas o saltarse la arquitectura de capas.

### 2. Fase de Especificación (QUÉ) - Skill `/c-spec`
- Ubicación: `.agents/specs/<nombre>-spec.md`
- Guía: [skills/c-spec/SKILL.md](skills/c-spec/SKILL.md) | Plantilla: [templates/spec-template.md](templates/spec-template.md)
- Regla: Agnóstico a la tecnología. Define historias de usuario, contratos de petición/respuesta JSON y criterios de aceptación en formato Gherkin.

### 3. Fase de Planificación Técnica (CÓMO) - Skill `/c-plan`
- Ubicación: `.agents/plans/<nombre>-plan.md`
- Guía: [skills/c-plan/SKILL.md](skills/c-plan/SKILL.md) | Plantilla: [templates/plan-template.md](templates/plan-template.md)
- Regla: Sin código de producción prematuro. Diseña clases (`@RestController`, `@Service`, `@Entity`, `@Repository`), DTOs, mappers y diagramas de secuencia Mermaid.

### 4. Fase de Tareas Verificables (EJECUCIÓN) - Skill `/c-task`
- Ubicación: `.agents/tasks/<nombre>-task.md`
- Guía: [skills/c-task/SKILL.md](skills/c-task/SKILL.md) | Plantilla: [templates/task-template.md](templates/task-template.md)
- Regla: Tareas atómicas, independientes y potencialmente paralelizadas con checkboxes `- [ ]` que se van marcando con `- [x]` al superar pruebas unitarias o de compilación (`./mvnw clean compile` o `./mvnw test`).
