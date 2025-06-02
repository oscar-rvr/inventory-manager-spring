# Usa una imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Crea un directorio para la app
WORKDIR /app

# Copia el jar compilado al contenedor
COPY target/users-service-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (opcional)
EXPOSE 8083

# Comando para ejecutar el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
