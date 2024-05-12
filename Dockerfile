# Maven
FROM jelastic/maven:3.9.5-openjdk-21
LABEL maintainer="Bruno La Porta Welausen <github.com/brunolpw>"
WORKDIR /app
COPY . .
RUN mvn clean package

ENV POSTGRES_PASSWORD=123

EXPOSE 8080
CMD [ "java", "-jar", "deck-of-cards-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod" ]

# # Java
# FROM openjdk:21
# LABEL maintainer="Bruno La Porta Welausen <github.com/brunolpw>"
# WORKDIR /app
# COPY target/*.jar /app/deck-of-cards-app.jar
# ENTRYPOINT [ "java", "-jar", "deck-of-cards-app.jar" ]
# EXPOSE 8080