FROM openjdk:11-jdk-slim-bullseye AS build-env
COPY build/libs/*.jar /app/
WORKDIR /app


# FROM gcr.io/distroless/java11-debian11:latest
FROM gcr.io/distroless/java11-debian11:debug
COPY --from=build-env /app /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "springbootstudy-1.0-SNAPSHOT.jar"]