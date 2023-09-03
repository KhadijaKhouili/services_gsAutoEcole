# Utilisez une image de base appropriée (Java avec support Alpine est recommandé)
FROM openjdk:17-alpine

WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/service-depenses-0.0.1-SNAPSHOT.jar /app/service-depense.jar

# Exposez le port sur lequel l'application Spring Boot écoute
EXPOSE 8084

# Exécutez l'application lorsque le conteneur démarre
ENTRYPOINT ["java", "-jar", "/app/service-depense.jar"]

