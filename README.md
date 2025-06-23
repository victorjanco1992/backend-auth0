# Proyecto: 🖼️ Galería Segura - Backend

Este es el backend de la aplicación **Galería Segura**, desarrollado en **Java con Spring Boot**. Provee una API REST protegida por JWT (Auth0) que permite a usuarios autenticados visualizar imágenes y a administradores eliminarlas o restaurarlas (simuladamente).

---

Integrantes: Janco Victor, Castillo Albert

---

## ✅ Requisitos

- Java 17
- Maven
- Cuenta en [Auth0](https://auth0.com/)
    - Aplicación SPA configurada
    - API registrada con Audience y roles (`admin`, `user`)
    - Action post-login configurada para enviar roles en el token
- Clonar el repositorio fronted para probar (https://github.com/victorjanco1992/frontend-auth0)

---

## ⚙️ Instalación y ejecución

1. Cloná este repositorio y navegá a la carpeta del backend:

   cd backend

2. Creá un archivo .env en la raíz de backend/ con tus variables de entorno (no se sube al repositorio):

   AUTH0_ISSUER=https://TU_DOMINIO.auth0.com/

   AUTH0_AUDIENCE=https://TU_AUDIENCE/

3. Ejecutá el proyecto con Maven
   Esto para cargar las variables

        Get-Content .env | Foreach-Object {
                $name, $value = $_ -split '='
                [Environment]::SetEnvironmentVariable($name, $value, "Process")
              }

   Y luego esto para ejecutar la app

        mvn spring-boot:run

   Esto levantará el backend en http://localhost:8080


🛡️ **Seguridad**

Este backend usa:

    JWT con validación de issuer y audience

    Reglas de acceso basadas en roles (ROLE_user, ROLE_admin)

    Validación de claims personalizados (https://galeria.example.com/roles)

    Spring Security con anotaciones @PreAuthorize

🧠 **Tecnologías utilizadas**

    Java 17

    Spring Boot

    Spring Security (OAuth2 Resource Server)

    Auth0

    Maven

🧪 **Endpoints disponibles**
| Método | Ruta                       | Acceso          | Descripción                          |
| ------ | -------------------------- | --------------- | ------------------------------------ |
| GET    | `/api/public`              | Público         | Mensaje sin autenticación            |
| GET    | `/api/protected`           | `user`, `admin` | Mensaje solo para usuarios logueados |
| GET    | `/api/admin`               | `admin`         | Mensaje solo para administradores    |
| GET    | `/api/images`              | `user`, `admin` | Lista de imágenes                    |
| DELETE | `/api/images/{id}`         | `admin`         | Marca una imagen como eliminada      |
| PATCH  | `/api/images/{id}/restore` | `admin`         | Restaura una imagen eliminada        |

