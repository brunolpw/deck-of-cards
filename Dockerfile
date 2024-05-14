# Maven
FROM jelastic/maven:3.9.5-openjdk-21 AS build
LABEL maintainer="Bruno La Porta Welausen <github.com/brunolpw>"
COPY /src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package

ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}

# Java
FROM openjdk:21-jdk
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
