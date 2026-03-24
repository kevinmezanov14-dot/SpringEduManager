# SpringEduManager

Gestor educativo desarrollado con Spring Boot.

## Tecnologías
- Java 21
- Spring Boot 3.4.1
- Spring MVC
- Spring Security con BCrypt
- Spring Data JPA
- Thymeleaf
- MySQL

## Configuración

1. Clonar el repositorio
2. Crear la base de datos en MySQL:
```sql
CREATE DATABASE edumanager;
```
3. Copiar `application.properties.example` a `application.properties`
4. Completar usuario y contraseña de MySQL
5. Ejecutar la aplicación desde Eclipse como Spring Boot App

## Usuarios de prueba
- **ADMIN:** usuario `admin` / contraseña `admin123`
- **USER:** usuario `user` / contraseña `user123`

## Estructura del proyecto
```
src/main/java/com/edu.manager/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── LoginController.java
│   ├── EstudianteController.java
│   └── CursoController.java
├── model/
│   ├── Estudiante.java
│   └── Curso.java
├── repository/
│   ├── EstudianteRepository.java
│   └── CursoRepository.java
└── service/
    ├── EstudianteService.java
    └── CursoService.java
```

## Funcionalidades
- Login y logout con Spring Security
- Roles ADMIN y USER
- CRUD de estudiantes
- CRUD de cursos (solo ADMIN puede agregar y eliminar)
- Base de datos MySQL con JPA
