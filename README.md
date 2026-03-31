# SpringEduManager

Aplicación web educativa desarrollada con el ecosistema de **Spring Framework**, orientada a la gestión de estudiantes, cursos, prácticas y evaluaciones en un bootcamp de programación.

Este proyecto corresponde a la **Evaluación del Módulo 6 – Desarrollo de aplicaciones JEE con Spring Framework**.

---

## Estructura del proyecto

```
EduManager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.edu.manager/
│   │   │       ├── config/           # Configuración general (beans, etc.)
│   │   │       ├── controllers/      # Controladores MVC y REST
│   │   │       ├── dtos/             # Objetos de transferencia de datos
│   │   │       ├── mappers/          # Mapeo entre entidades y DTOs
│   │   │       ├── models/           # Entidades JPA (Estudiante, Curso, etc.)
│   │   │       ├── repositories/     # Repositorios JPA
│   │   │       ├── security/         # Configuración de Spring Security
│   │   │       └── services/         # Lógica de negocio
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/              # Estilos CSS
│   │       │   └── images/           # Imágenes (cursos, estudiantes, etc.)
│   │       └── templates/
│   │           ├── cursos/           # Vistas de cursos
│   │           ├── error/            # Páginas de error
│   │           ├── estudiantes/      # Vistas de estudiantes
│   │           ├── evaluaciones/     # Vistas de evaluaciones
│   │           ├── fragments/        # Fragmentos reutilizables (Thymeleaf)
│   │           ├── practicas/        # Vistas de prácticas
│   │           ├── index.html        # Página principal
│   │           ├── login.html        # Formulario de login
│   │           └── registro.html     # Formulario de registro
│   └── test/java/                    # Tests unitarios e integración
├── target/                           # Artefactos generados por Maven
├── pom.xml                           # Dependencias y configuración Maven
├── application.properties            # Configuración de la aplicación
├── application.properties.example    # Plantilla de configuración
└── application-test.properties       # Configuración para entorno de test
```

---

## Tecnologías utilizadas

| Tecnología | Rol |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot | Framework base |
| Spring MVC | Capa de presentación y controladores |
| Spring Data JPA | Acceso y persistencia de datos |
| Spring Security | Autenticación y autorización |
| Thymeleaf | Motor de plantillas HTML |
| H2 / MySQL | Base de datos embebida / producción |
| Maven | Gestor de dependencias y ciclo de vida |
| REST | Exposición de servicios externos |

---

## Configuración y ejecución

### Prerrequisitos

- Java 21+
- Maven 3.8+
- MySQL 

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/SpringEduManager.git
cd SpringEduManager
```

### 2. Configurar las propiedades

Copiar el archivo de ejemplo y ajustar según el entorno:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Editar `application.properties` con los datos de tu base de datos y credenciales.

### 3. Compilar y ejecutar

```bash
# Limpiar y compilar
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

### 4. Acceder a la aplicación

```
http://localhost:8080
```

---

## Seguridad y roles

La aplicación implementa autenticación y autorización con **Spring Security**.

| Rol | Permisos |
|---|---|
| `ADMIN` | Acceso total: crear, editar y eliminar cursos, estudiantes y evaluaciones |
| `USER` | Acceso de solo lectura a cursos, prácticas y evaluaciones propias |

- El login se realiza en `/login`
- El logout está disponible desde el menú principal
- Las rutas de administración están protegidas con configuración en `SecurityConfig`

---

## API REST

La aplicación expone endpoints RESTful para integración con sistemas externos como por ejemplo.

### Estudiantes

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/estudiantes` | Listar todos los estudiantes |
| GET | `/api/estudiantes/{id}` | Obtener estudiante por ID |
| POST | `/api/estudiantes` | Registrar nuevo estudiante |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante |

### Cursos

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/cursos` | Listar todos los cursos |
| GET | `/api/cursos/{id}` | Obtener curso por ID |
| POST | `/api/cursos` | Crear nuevo curso |
| PUT | `/api/cursos/{id}` | Actualizar curso |
| DELETE | `/api/cursos/{id}` | Eliminar curso |

> Los endpoints pueden consumirse desde **Postman** o cualquier cliente HTTP.

---

## Arquitectura

El proyecto sigue el patrón **MVC en capas**:

```
Controller  →  Service  →  Repository  →  Base de datos
     ↓              ↓
  (REST)        (DTO / Mapper)
```

- **Controllers**: reciben las peticiones HTTP (web y REST)
- **Services**: contienen la lógica de negocio
- **Repositories**: interactúan con la base de datos vía JPA
- **DTOs + Mappers**: separan la capa de presentación de la capa de dominio
- **Models**: entidades persistidas (Estudiante, Curso, Evaluacion, Practica)
- **Security**: configura autenticación, roles y protección de rutas
- **Config**: beans y configuraciones adicionales

---

## Etapas del proyecto (Lecciones)

| Lección | Contenido |
|---|---|
| 1 – Maven | Creación del proyecto con Spring Initializr y configuración del `pom.xml` |
| 2 – Spring MVC | Implementación del patrón MVC con controladores, vistas y formularios |
| 3 – Spring Data JPA | Repositorios, servicios y persistencia con MySQL |
| 4 – Spring Security | Login, logout, roles y protección de rutas |
| 5 – REST | Exposición de APIs RESTful y consumo desde cliente externo |

---


## Licencia

Proyecto educativo desarrollado en el marco del **Bootcamp de Programación – Módulo 6**.

## Autor
Kevin Meza Catril 
