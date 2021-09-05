FROM openjdk:16-jdk-alpine
MAINTAINER Rafael
COPY target/aluraflix-0.0.1-SNAPSHOT.jar aluraflix.jar
ENTRYPOINT ["java", "-jar", "/aluraflix.jar"]