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
2. Construir y levantar los contenedores:
```
docker compose up --build
```