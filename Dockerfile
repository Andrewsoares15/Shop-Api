FROM openjdk:11
MAINTAINER Andrew Soares
COPY build/libs/shop-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE	8080
ENTRYPOINT ["java", "-jar", "app.jar"]