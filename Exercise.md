# Consultas con Query Methods y Multiples Joins en Spring Data JPA

En Spring Data JPA se pueden realizar consultas con múltiples joins usando navegación de propiedades con el guion bajo `_` y combinando condiciones con la palabra clave `And`.

---

### 1. Permisos segun el correo del usuario y el nombre del rol
Quiero obtener todos los permisos de un usuario segun su correo electronico y el nombre de su rol.

<details>
<summary>Ver solución</summary>

Repositorio: `PermissionRepository`

```java
List<Permission> findDistinctByRolePermissions_Role_Users_EmailAndRolePermissions_Role_Name(String email, String roleName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT p FROM Permission p
JOIN p.rolePermissions rp
JOIN rp.role r
JOIN r.users u
WHERE u.email = :email AND r.name = :roleName
```
</details>

---

### 2. Usuarios segun el rol y un permiso asignado
Quiero obtener todos los usuarios segun el nombre de su rol y el nombre de un permiso especifico que tengan asignado.

<details>
<summary>Ver solución</summary>

Repositorio: `UserRepository`

```java
List<User> findDistinctByRole_NameAndRole_RolePermissions_Permission_Name(String roleName, String permissionName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT u FROM User u
JOIN u.role r
JOIN r.rolePermissions rp
JOIN rp.permission p
WHERE r.name = :roleName AND p.name = :permissionName
```
</details>

---

### 3. Rutinas segun el usuario y un ejercicio incluido
Quiero obtener todas las rutinas creadas por un usuario segun el correo del usuario y el nombre de un ejercicio que este incluido en la rutina.

<details>
<summary>Ver solución</summary>

Repositorio: `RoutineRepository`

```java
List<Routine> findDistinctByUser_EmailAndRoutineExercises_Exercise_Name(String email, String exerciseName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT r FROM Routine r
JOIN r.user u
JOIN r.routineExercises re
JOIN re.exercise e
WHERE u.email = :email AND e.name = :exerciseName
```
</details>

---

### 4. Ejercicios segun su tipo y el usuario que los tiene en sus rutinas
Quiero obtener todos los ejercicios segun el tipo de ejercicio y el nombre del usuario que creo la rutina donde se utiliza.

<details>
<summary>Ver solución</summary>

Repositorio: `ExerciseRepository`

```java
List<Exercise> findDistinctByTypeAndRoutineExercises_Routine_User_Name(String type, String userName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT e FROM Exercise e
JOIN e.routineExercises re
JOIN re.routine r
JOIN r.user u
WHERE e.type = :type AND u.name = :userName
```
</details>

---

### 5. Registros de actividad segun el nombre de la rutina y el correo del usuario
Quiero obtener todos los registros de actividad segun el nombre de la rutina ejecutada y el correo del usuario que realizo la actividad.

<details>
<summary>Ver solución</summary>

Repositorio: `ActivityLogRepository`

```java
List<ActivityLog> findByRoutine_NameAndUser_Email(String routineName, String email);
```

Equivalente en JPQL:
```sql
SELECT a FROM ActivityLog a
JOIN a.routine r
JOIN a.user u
WHERE r.name = :routineName AND u.email = :email
```
</details>

---

### 6. Registros de actividad segun el ejercicio realizado y el rol del usuario
Quiero obtener todos los registros de actividad segun el nombre del ejercicio realizado y el rol del usuario que ejecuto la actividad.

<details>
<summary>Ver solución</summary>

Repositorio: `ActivityLogRepository`

```java
List<ActivityLog> findDistinctByActivityExercises_RoutineExercise_Exercise_NameAndUser_Role_Name(String exerciseName, String roleName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT a FROM ActivityLog a
JOIN a.activityExercises ae
JOIN ae.routineExercise re
JOIN re.exercise e
JOIN a.user u
JOIN u.role r
WHERE e.name = :exerciseName AND r.name = :roleName
```
</details>

---

### 7. Usuarios segun el nombre de la rutina y un ejercicio contenido
Quiero obtener todos los usuarios segun el nombre de una rutina que hayan creado y el nombre de un ejercicio que forme parte de esa rutina.

<details>
<summary>Ver solución</summary>

Repositorio: `UserRepository`

```java
List<User> findDistinctByRoutines_NameAndRoutines_RoutineExercises_Exercise_Name(String routineName, String exerciseName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT u FROM User u
JOIN u.routines r
JOIN r.routineExercises re
JOIN re.exercise e
WHERE r.name = :routineName AND e.name = :exerciseName
```
</details>

---

### 8. Ejercicios de rutina segun el tipo de ejercicio y el correo del dueño
Quiero obtener todos los ejercicios configurados en rutinas segun el tipo del ejercicio y el correo del usuario propietario de la rutina.

<details>
<summary>Ver solución</summary>

Repositorio: `RoutineExerciseRepository`

```java
List<RoutineExercise> findByExercise_TypeAndRoutine_User_Email(String exerciseType, String email);
```

Equivalente en JPQL:
```sql
SELECT re FROM RoutineExercise re
JOIN re.exercise e
JOIN re.routine r
JOIN r.user u
WHERE e.type = :exerciseType AND u.email = :email
```
</details>

---

### 9. Ejercicios ejecutados segun el nombre del ejercicio y el correo del usuario
Quiero obtener el detalle de ejercicios ejecutados en actividades segun el nombre del ejercicio base y el correo del usuario que registro la actividad.

<details>
<summary>Ver solución</summary>

Repositorio: `ActivityExerciseRepository`

```java
List<ActivityExercise> findByRoutineExercise_Exercise_NameAndActivityLog_User_Email(String exerciseName, String email);
```

Equivalente en JPQL:
```sql
SELECT ae FROM ActivityExercise ae
JOIN ae.routineExercise re
JOIN re.exercise e
JOIN ae.activityLog al
JOIN al.user u
WHERE e.name = :exerciseName AND u.email = :email
```
</details>

---

### 10. Ejercicios segun el nombre de la rutina y el rol del usuario creador
Quiero obtener todos los ejercicios segun el nombre de la rutina donde se usan y el rol del usuario creador de la rutina.

<details>
<summary>Ver solución</summary>

Repositorio: `ExerciseRepository`

```java
List<Exercise> findDistinctByRoutineExercises_Routine_NameAndRoutineExercises_Routine_User_Role_Name(String routineName, String roleName);
```

Equivalente en JPQL:
```sql
SELECT DISTINCT e FROM Exercise e
JOIN e.routineExercises re
JOIN re.routine r
JOIN r.user u
JOIN u.role ro
WHERE r.name = :routineName AND ro.name = :roleName
```
</details>
