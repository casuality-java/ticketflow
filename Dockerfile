# Stage 1: сборка
FROM gradle:9.7.1-jdk21 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle :core:bootJar --no-daemon

# Stage 2: рантайм
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/core/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]