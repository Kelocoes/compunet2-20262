# [SPEC-XXX] Nombre de la Especificación

## Metadatos
- ID: SPEC-XXX
- Fecha: YYYY-MM-DD
- Autor: [Nombre / Agente]
- Estado: [DRAFT | REVIEW | APPROVED]

## Contexto y Objetivo
Descripción general del problema a resolver o de la funcionalidad requerida.

## Historias de Usuario

### HU-01: [Título de la Historia]
- Como: [Rol de usuario o sistema]
- Quiero: [Acción o requerimiento específico]
- Para: [Beneficio o valor esperado]

## Contratos de API (HTTP Endpoints)

### 1. [Nombre del Endpoint]
- Método: `POST | GET | PUT | PATCH | DELETE`
- Ruta: `/compunet2-2026/api/v1/ejemplo`
- Autenticación: `Bearer JWT | Público | Role: [ROLE_ADMIN]`
- Códigos de Respuesta Esperados:
  - `200 OK` / `201 Created`: Operación exitosa.
  - `400 Bad Request`: Error de validación de campos.
  - `401 Unauthorized`: Token ausente o inválido.
  - `403 Forbidden`: Permisos insuficientes.
  - `404 Not Found`: Recurso no encontrado.

#### Esquema Request (JSON)
```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "type": "object",
  "properties": {
    "nombre": {
      "type": "string"
    },
    "activo": {
      "type": "boolean"
    }
  },
  "required": ["nombre"]
}
```

#### Esquema Response (JSON)
```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "type": "object",
  "properties": {
    "id": {
      "type": "integer"
    },
    "nombre": {
      "type": "string"
    },
    "fechaCreacion": {
      "type": "string",
      "format": "date-time"
    }
  },
  "required": ["id", "nombre", "fechaCreacion"]
}
```

## Criterios de Aceptación (Gherkin)

```gherkin
Feature: [Nombre de la funcionalidad]

  Scenario: [Caso de éxito principal]
    Given que el usuario está autenticado con rol "[ROL]"
    When envía una petición POST a "/compunet2-2026/api/v1/ejemplo" con los datos válidos
    Then la respuesta debe tener código de estado 201
    And el cuerpo de respuesta debe incluir el identificador generado
    And el recurso debe quedar persistido en la base de datos

  Scenario: [Caso de error por datos inválidos]
    Given que el usuario está autenticado
    When envía una petición POST a "/compunet2-2026/api/v1/ejemplo" sin el campo obligatorio "nombre"
    Then la respuesta debe tener código de estado 400
    And el cuerpo de error debe detallar que el campo "nombre" es requerido
```
