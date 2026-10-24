---
name: c-spec
description: Guía y genera especificaciones funcionales agnósticas a la tecnología guardadas en .agents/specs/<nombre>-spec.md, incluyendo historias de usuario, contratos JSON de petición/respuesta y criterios Gherkin.
---

# Skill: /c-spec (Especificación Funcional)

Esta skill guía al desarrollador o agente en la creación y refinamiento de especificaciones funcionales que capturan el **QUÉ** de una necesidad o funcionalidad.

## Principios Fundamentales
1. **Agnóstico a la tecnología**: La especificación no debe mencionar frameworks concretos, anotaciones de código ni nombres de clases Java o librerías internas. Se centra en el comportamiento observable del sistema, usuarios y contratos de integración.
2. **Convención de nomenclatura**: El archivo generado debe guardarse obligatoriamente bajo:
   ```
   .agents/specs/<nombre-funcionalidad>-spec.md
   ```
   Ejemplo: `.agents/specs/auth-user-spec.md`, `.agents/specs/routine-management-spec.md`.
3. **Uso de plantilla**: Debe tomar como base la estructura definida en [`.agents/templates/spec-template.md`](../templates/spec-template.md).
4. **Cero emojis**: Mantener un estilo técnico formal y limpio.

---

## Flujo de Trabajo para /c-spec

### 1. Levantamiento de Requerimientos
Preguntar o deducir con el desarrollador:
- Cuál es el objetivo del negocio o problema funcional.
- Quiénes son los actores involucrados (usuarios finales, administradores, servicios externos).
- Cuáles son los casos de uso esperados y las condiciones de error / bordes.

### 2. Definición de Historias de Usuario
Seguir estrictamente el formato estándar:
- **Como**: [Rol de usuario o actor del sistema]
- **Quiero**: [Acción o requerimiento específico]
- **Para**: [Beneficio o valor de negocio esperado]

### 3. Definición de Contratos de API
Para cada operación expuesta por el sistema:
- Método HTTP (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`).
- Ruta relativa del recurso (ej. `/api/v1/recurso`).
- Políticas de autenticación y autorización requeridas.
- Códigos de respuesta HTTP esperados (ej. 200, 201, 400, 401, 403, 404, 409).
- Esquema JSON de Petición (Request Body) con validaciones y tipos de datos.
- Esquema JSON de Respuesta (Response Body) con campos mínimos requeridos.

### 4. Criterios de Aceptación en Formato Gherkin
Redactar escenarios claros utilizando sintaxis Gherkin (`Given`, `When`, `Then`, `And`):
- Escenarios de éxito (camino feliz).
- Escenarios de validación fallida (campos faltantes, formatos inválidos).
- Escenarios de seguridad y acceso no autorizado o prohibido.
- Escenarios de datos no encontrados o estados conflictivos.

---

## Ejemplo de Invocación y Generación

Al invocar `/c-spec` con una descripción de funcionalidad:
1. El agente revisa si ya existe una especificación similar en `.agents/specs/`.
2. Genera o actualiza el archivo `.agents/specs/<nombre>-spec.md`.
3. Solicita confirmación al usuario antes de dar paso a la fase de planificación (`/c-plan`).
