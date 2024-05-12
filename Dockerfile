# Maven
FROM jelastic/maven:3.9.5-openjdk-21
LABEL maintainer="Bruno La Porta Welausen <github.com/brunolpw>"
WORKDIR /app
COPY . .
RUN mvn clean package

ENV POSTGRES_PASSWORD=123

# Java
FROM openjdk:21
LABEL maintainer="Bruno La Porta Welausen <github.com/brunolpw>"
WORKDIR /app
COPY target/*.jar /app/deck-of-cards-app.jar
ENTRYPOINT [ "java", "-jar", "deck-of-cards-app.jar" ]
EXPOSE 8080