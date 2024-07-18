FROM openjdk:17-alpine
ARG SERVICE_NAME
COPY ${SERVICE_NAME}/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app.jar"]