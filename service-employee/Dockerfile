# # Utilisez une image de base appropriée (Java avec support Alpine est recommandé)
# FROM maven:latest AS maven

# WORKDIR /usr/src/app
# COPY . /usr/src/app
# # Compile and package the application to an executable JAR
# RUN mvn clean package 

# # Utilisez l'image OpenJDK comme image finale
# FROM openjdk:17-alpine

# ARG JAR_FILE=service-emp.jar

# WORKDIR /opt/app

# # Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
# COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

# # Exposez le port sur lequel l'application Spring Boot écoute
# EXPOSE 8083

# # Exécutez l'application lorsque le conteneur démarre
# ENTRYPOINT ["java", "-jar", "service-emp.jar"]



















# Utilisez une image de base appropriée (Java avec support Alpine est recommandé)
FROM openjdk:17-alpine

# WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/service-employee-0.0.1-SNAPSHOT.jar /app/service-emp.jar

# Exposez le port sur lequel l'application Spring Boot écoute
EXPOSE 8083

# Exécutez l'application lorsque le conteneur démarre
CMD ["java", "-jar", "/app/service-emp.jar"]