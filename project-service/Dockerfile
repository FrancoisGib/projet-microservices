FROM openjdk:17-alpine

ENV ENVIRONMENT=default

LABEL author="FrancoisGib"
EXPOSE 8082
EXPOSE 5672

COPY project-service/target/*.jar project-service.jar
ENTRYPOINT java -jar -Dspring.profiles.active=$ENVIRONMENT project-service.jar