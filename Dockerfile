@@ -1,12 +1,13 @@
# Etapa de construcción
FROM maven:3.8.5-openjdk-17 AS build
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
