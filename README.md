
### Tarea: Submodules Integration with HTTP Communication

---

### Servicios involucrados

- `users-service`
- `employees-service`
- `assets-service`
- `asset-movements-service`
- `discovery-server`

---

### 1. Service Discovery con Eureka

- [x] Todos los microservicios están registrados en `discovery-server`
- [x] Verificación visual desde `http://localhost:8761`
- [x] Comunicación entre servicios usando nombres lógicos:  
  `http://employees-service`, `http://users-service`, etc.

---

### 2. Configuración de WebClient con LoadBalancer

- [x] Declarado `WebClient.Builder` con `@LoadBalanced` en configuración:

```java
@Bean
@LoadBalanced
public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
}
```

---

### 3. Comunicación HTTP entre servicios

#### Desde `assets-service`:

- [x] Llamada a `asset-movements-service`:
    - Endpoint: `/v1/assets/{id}/movements`
    - Usa WebClient para comunicarse con:  
      `http://asset-movements-service/v1/movements/asset/{id}`

#### Desde `asset-movements-service`:

- [x] Llamadas de validación a:
    - `users-service` → valida existencia de usuario
    - `employees-service` → valida existencia de empleado
    - `assets-service` → valida existencia de activo

---

### 4. Pruebas manuales completadas

- [x] POST `/v1/movements` con header `X-User-Id`
- [x] GET `/v1/movements/asset/{id}` devuelve movimientos
- [x] Manejo de errores 400 con `@RestControllerAdvice`
- [x] Validaciones correctas en `AssetMovementsDTO`

---

### Resultado actual

El sistema permite registrar movimientos de activos, validando por HTTP la existencia de los recursos relacionados (usuario, empleado, activo) y listarlos desde cualquier microservicio utilizando `WebClient` y `Service Discovery`.
