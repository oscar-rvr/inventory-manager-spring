# Instrucciones para levantar el contenedor de produccion y desarrollo 

## PRODUCCION

Existe el archivo ``` .env.prod ``` este contiene los las credenciales para levantar el ``` .yml ```
debido a que existen tambien los archivo de desarollo se debe hacer especificar donde estan las conexiones.
con:

``` 
docker-compose --env-file ./.env.prod -f docker-compose.prod.yml up
```

## DESARROLLO
Para levantar el contenedor de desarollo solo necesitas usar el comando de siempre
```
docker-compose up
```
