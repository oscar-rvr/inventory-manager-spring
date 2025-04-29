# Instrucciones para levantar el contenedor de produccion y desarrollo 

## PRODUCCION

Existe el archivo ``` .env.prod ``` este contiene los las credenciales para levantar el ``` .yml ```
debido a que existen tambien los archivo de desarollo se debe hacer especificar donde estan las conexiones.
con:

``` 
docker-compose --env-file ./.env.prod -f docker-compose.prod.yml up -d
```

## DESARROLLO
Para levantar el contenedor de desarollo solo necesitas usar el comando de siempre
```
docker-compose --env-file ./.env -f docker-compose.yml up -d
```

## LOCAL
Para usar el perfil local que funciona con h2, se cambia en properties el activo y se accede con 
``` 
http://localhost:8079/h2-console
```
## ESTRUCTURA
| PERFIL  | APP                              | PUERTO -> DOCKER |
|---------|----------------------------------|------------------|
| LOCAL   | http://localhost:8079/h2-console | ---------------- |
| DEV     | http://localhost:8080/employees  | 5436 -> 5432     |
| PROD    | http://localhost:8081/employees  | 5433 -> 5432     |
