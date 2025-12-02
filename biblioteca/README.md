# Sistema para Biblioteca – API REST

Sistema backend para la gestión de bibliotecas universitarias mediante Java y Spring Boot.
Incluye control de usuarios, préstamo de libros, roles, autenticación JWT y documentación con Swagger.

# Contexto del Proyecto

Las universidades suelen gestionar el préstamo de libros y la administración de bibliotecas de forma manual o con software limitado. Esto dificulta:

El control eficiente del inventario de libros.

El seguimiento de préstamos .

La gestión de usuarios y sus permisos.

La seguridad al acceder a funciones.

Para resolverlo, se desarrolla una API RESTful robusta, segura y escalable, que permita a administradores, bibliotecarios y usuarios consultar, registrar y gestionar toda la actividad de una biblioteca universitaria.

# Objetivos del Sistema

Gestionar libros, usuarios, roles y préstamos.

Proveer autenticación segura mediante JWT.

Controlar el acceso según roles: ADMIN, USUARIO, BIBLIOTECARIO.

Exponer endpoints REST para integración con frontend o aplicaciones móviles.

Documentar todas las rutas con Swagger.

# Arquitectura y Tecnologías

Java 17 / 21	Lenguaje principal
Spring Boot	Framework base
Spring Web MVC	Creación de controladores REST
Spring Security	Seguridad y control de roles
JWT (JSON Web Tokens)	Autenticación sin sesiones
Spring Data JPA	Persistencia de datos
Hibernate	ORM
PostgreSQL	Base de datos relacional
Lombok	Reducción de boilerplate
Swagger (Springdoc OpenAPI)	Documentación interactiva
Maven	Gestión de dependencias

# Documentación con Swagger

Una vez integrado Swagger, podrás acceder a la documentación en:

http://localhost:8080/swagger-ui/index.html

# Cómo Ejecutar el Proyecto
1. Clonar el repositorio
git clone https://github.com/R-alber-H/SistemaBiblioteca.git

2. Ir al directorio
cd biblioteca

3. Configurar base de datos

En application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update

4. Ejecutar
mvn spring-boot:run