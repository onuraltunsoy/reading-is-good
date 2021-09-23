FROM adoptopenjdk/openjdk11:latest
MAINTAINER "Docker App onuraltunsoy>"
WORKDIR /app

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker","/app/app.jar"]

EXPOSE 8080