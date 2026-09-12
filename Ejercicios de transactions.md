# Ejercicios de Transacciones y Rollback

Este documento contiene dos ejercicios practicos para comprender y aplicar el manejo de transacciones con @Transactional en Spring Boot, garantizando la atomicidad de las operaciones.

---

## Ejercicio 1: Creacion de Rol con Asignacion Masiva de Permisos

### Contexto
Se requiere registrar un nuevo Rol en el sistema junto con una lista de IDs de permisos que se deben asociar a traves de la entidad RolePermission.

### Requerimientos
1. Crear un metodo en el servicio de roles anotado con @Transactional(rollbackFor = Exception.class).
2. El metodo debe recibir el nombre del nuevo rol y una lista de enteros con los IDs de permisos (List<Integer> permissionIds).
3. Guardar primero el Rol en la base de datos para obtener su ID generado.
4. Iterar sobre la lista de IDs de permisos:
   - Buscar cada Permiso en la base de datos por su ID.
   - Si un ID de permiso no existe o si la lista contiene un ID invalido (por ejemplo, menor o igual a cero), lanzar una excepcion de negocio (por ejemplo, EntityNotFoundException o una excepcion personalizada).
   - Si el permiso existe, crear y guardar el registro correspondiente en la tabla role_permissions.
5. Si ocurre cualquier error durante la asociacion de cualquiera de los permisos:
   - La transaccion debe abortarse por completo.
   - Ni el Rol ni ninguno de los registros de role_permissions creados previamente deben quedar guardados en la base de datos.

---

## Ejercicio 2: Creacion de Rutina con Ejercicios Asociados

### Contexto
Un usuario desea registrar una nueva Rutina de entrenamiento que incluye el detalle de varios ejercicios a realizar (routine_exercises), indicando series, repeticiones y peso.

### Requerimientos
1. Crear un metodo en el servicio de rutinas anotado con @Transactional(rollbackFor = Exception.class).
2. El metodo debe recibir el ID del usuario propietario, los datos de la rutina (nombre y descripcion) y una lista de ejercicios a programar.
3. Validar que el usuario exista; si no existe, lanzar una excepcion.
4. Guardar la entidad Routine vinculada al usuario.
5. Procesar la lista de ejercicios de la rutina:
   - Validar que cada ejercicio exista en el catalogo de ejercicios (Exercise).
   - Validar que los valores de target_sets y target_reps sean mayores a cero. Si algun ejercicio tiene valores invalidos (por ejemplo, series <= 0), lanzar una excepcion.
   - Guardar cada RoutineExercise asociado a la rutina creada.
6. Si la validacion de un ejercicio falla a mitad del proceso:
   - La transaccion debe hacer rollback inmediato.
   - La rutina principal y los ejercicios guardados antes del error no deben persistir en la base de datos.
