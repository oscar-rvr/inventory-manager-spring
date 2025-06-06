# Inventory Manager - Microservices

Este proyecto contiene una arquitectura de microservicios basada en Spring Boot para gestionar usuarios, empleados, activos y movimientos de activos.

## Microservicios incluidos

- **users-service** (`8083`)
- **employees-service** (`8082`)
- **assets-service** (`8081`)
- **asset-movements-service** (`8080`)
- **postgres** (base de datos compartida `managementDB`)

## Cómo levantar el sistema completo

1. Compilar los JARs (desde la raíz):

```
mvn clean package 
```
2. Construir imgs
```
docker compose up --build
```
3. Levantar las imgs

```
docker-compose up
```
eureka
http://localhost:8761/

docker stacktrace
docker logs asset-movements-service
----
mvn clean install
docker-compose down
docker-compose up --build



----
Si estás en tu proyecto y usas Mac/Linux, escribe esto en la raíz del proyecto:

tree -L 3 src/main/java