# [SPEC-001] Gestión de Roles en Interfaz MVC

## Metadatos
- ID: SPEC-001
- Fecha: 2026-10-24
- Autor: Antigravity
- Estado: APPROVED

## Contexto y Objetivo
El sistema requiere una interfaz web interactiva basada en servidor (MVC) que permita a los administradores gestionar los roles del sistema. La gestión comprende el listado de roles existentes, la creación de nuevos roles, la edición de roles ya registrados y la eliminación de roles que ya no sean necesarios.

## Historias de Usuario

### HU-01: Listado de Roles
- Como: Administrador del sistema
- Quiero: Ver una pantalla con la lista de todos los roles registrados
- Para: Conocer los roles disponibles y acceder a las opciones de creación, edición y eliminación.

### HU-02: Creación de un Nuevo Rol
- Como: Administrador del sistema
- Quiero: Acceder a un formulario para ingresar los datos de un nuevo rol y guardarlo
- Para: Habilitar nuevas funciones o niveles de acceso en la plataforma.

### HU-03: Edición de un Rol Existente
- Como: Administrador del sistema
- Quiero: Cargar los datos actuales de un rol en un formulario, modificar su nombre y actualizarlo
- Para: Mantener la información de los roles alineada con las necesidades de la organización.

### HU-04: Eliminación de un Rol
- Como: Administrador del sistema
- Quiero: Eliminar un rol existente desde el listado
- Para: Retirar del sistema aquellos roles obsoletos o en desuso.

## Contratos de Navegación y Endpoints Web (MVC)

### 1. Vista de Listado de Roles
- Método: `GET`
- Ruta: `/mvc/roles`
- Autenticación: Rol Administrador / Usuario autenticado
- Parámetros de entrada: Ninguno
- Códigos de Respuesta Esperados:
  - `200 OK`: Renderiza la vista HTML con la tabla de roles y acciones disponibles.
  - `401 Unauthorized`: Usuario no autenticado.
  - `403 Forbidden`: Usuario sin permisos suficientes.

### 2. Formulario de Creación de Rol
- Método: `GET`
- Ruta: `/mvc/roles/add`
- Autenticación: Rol Administrador
- Parámetros de entrada: Ninguno
- Códigos de Respuesta Esperados:
  - `200 OK`: Renderiza la vista HTML con el formulario vacío para ingresar el nombre del rol.
  - `403 Forbidden`: Acceso no autorizado.

### 3. Procesar Creación de Rol
- Método: `POST`
- Ruta: `/mvc/roles/add`
- Autenticación: Rol Administrador
- Contenido Formulario (form-urlencoded):
  - `name`: Cadena de texto (requerido, no vacío).
- Códigos de Respuesta Esperados:
  - `302 Found`: Redirección a `/mvc/roles` tras crear exitosamente el rol.
  - `400 Bad Request` / Recarga de vista con errores: Cuando el nombre del rol no cumple las validaciones.

### 4. Formulario de Edición de Rol
- Método: `GET`
- Ruta: `/mvc/roles/edit/{id}`
- Autenticación: Rol Administrador
- Parámetros de ruta:
  - `id`: Identificador numérico del rol.
- Códigos de Respuesta Esperados:
  - `200 OK`: Renderiza la vista HTML con los datos del rol cargados en el formulario.
  - `404 Not Found`: Si el identificador no corresponde a un rol existente.

### 5. Procesar Edición de Rol
- Método: `POST` (o `PUT` vía simulación de método en formulario)
- Ruta: `/mvc/roles/edit/{id}`
- Autenticación: Rol Administrador
- Parámetros de ruta:
  - `id`: Identificador numérico del rol.
- Contenido Formulario (form-urlencoded):
  - `name`: Cadena de texto (requerido, no vacío).
- Códigos de Respuesta Esperados:
  - `302 Found`: Redirección a `/mvc/roles` tras actualizar exitosamente el rol.
  - `400 Bad Request` / Recarga de vista: Validación fallida del formulario.
  - `404 Not Found`: Si el rol a modificar no existe.

### 6. Eliminación de Rol
- Método: `POST` (o `DELETE` vía simulación de método)
- Ruta: `/mvc/roles/delete/{id}`
- Autenticación: Rol Administrador
- Parámetros de ruta:
  - `id`: Identificador numérico del rol a eliminar.
- Códigos de Respuesta Esperados:
  - `302 Found`: Redirección a `/mvc/roles` tras eliminar el registro.
  - `404 Not Found`: Si el rol a eliminar no existe.

## Esquemas de Datos del Dominio (Agnósticos)

### Esquema de Formulario de Rol
```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "title": "RoleFormData",
  "type": "object",
  "properties": {
    "name": {
      "type": "string",
      "minLength": 1,
      "maxLength": 50,
      "description": "Nombre representativo del rol"
    }
  },
  "required": ["name"]
}
```

### Esquema de Visualización de Rol
```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "title": "RoleItemView",
  "type": "object",
  "properties": {
    "id": {
      "type": "integer",
      "description": "Identificador único del rol"
    },
    "name": {
      "type": "string",
      "description": "Nombre del rol"
    }
  },
  "required": ["id", "name"]
}
```

## Criterios de Aceptación (Gherkin)

```gherkin
Feature: Gestión de Roles en la Interfaz Web MVC

  Scenario: Consultar la lista de roles
    Given que el usuario cuenta con permisos de administración
    When solicita la ruta web "/mvc/roles" mediante GET
    Then el sistema responde con estado HTTP 200
    And visualiza la tabla con los roles registrados y las opciones de "Agregar", "Editar" y "Eliminar"

  Scenario: Cargar formulario de creación de rol
    Given que el usuario administrador se encuentra en la pantalla de listado de roles
    When hace clic en la acción de agregar rol o navega a "/mvc/roles/add"
    Then el sistema responde con estado HTTP 200
    And presenta un formulario con el campo para ingresar el nombre del rol

  Scenario: Crear un nuevo rol exitosamente
    Given que el usuario administrador está en el formulario de creación de rol
    When envía el formulario con un nombre válido como "ROLE_MANAGER"
    Then el sistema registra el nuevo rol
    And redirige al usuario a la ruta "/mvc/roles" con estado HTTP 302
    And el nuevo rol "ROLE_MANAGER" aparece en el listado

  Scenario: Intentar crear un rol con datos inválidos
    Given que el usuario administrador está en el formulario de creación de rol
    When envía el formulario con el campo de nombre vacío
    Then el sistema no persiste el rol
    And permanece en la vista de formulario mostrando un mensaje de validación

  Scenario: Cargar formulario de edición de rol existente
    Given que existe un rol con identificador 1 y nombre "ROLE_ADMIN"
    When el usuario administrador accede a la ruta "/mvc/roles/edit/1"
    Then el sistema responde con estado HTTP 200
    And muestra el formulario con el valor "ROLE_ADMIN" precargado

  Scenario: Actualizar un rol exitosamente
    Given que existe un rol con identificador 1
    When el usuario envía una solicitud a "/mvc/roles/edit/1" con el nuevo nombre "ROLE_SUPERADMIN"
    Then el sistema actualiza la información del rol
    And redirige a la ruta "/mvc/roles" con estado HTTP 302
    And el listado muestra el nombre actualizado "ROLE_SUPERADMIN"

  Scenario: Eliminar un rol existente
    Given que existe un rol con identificador 2
    When el usuario solicita la eliminación en "/mvc/roles/delete/2"
    Then el sistema remueve el rol del repositorio de datos
    And redirige a la ruta "/mvc/roles" con estado HTTP 302
    And el rol con identificador 2 ya no figura en el listado
```