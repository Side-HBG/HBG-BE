FROM openjdk:21-jdk-slim-bullseye AS build-env
ARG BUILD_VERSION

ENV BUILD_VERSION=${BUILD_VERSION}

COPY build/libs/springbootstudy-${BUILD_VERSION}-SNAPSHOT.jar /app/app.jar

WORKDIR /app

FROM gcr.io/distroless/java21-debian12:debug

ENV HGB_JDBC_HOST=localhost
ENV HGB_JDBC_PORT=5432
ENV HGB_JDBC_DB=hgb
ENV HGB_JDBC_USER=hgb
ENV HGB_JDBC_PASSWORD=hgb
ENV HGB_JDBC_URL=jdbc:postgresql://${HGB_JDBC_HOST}:${HGB_JDBC_PORT}/${HGB_JDBC_DB}

COPY --from=build-env /app /app

WORKDIR /app

ENTRYPOINT ["java","-jar","/app/app.jar"]

