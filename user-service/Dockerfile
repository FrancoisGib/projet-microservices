FROM openjdk:17-alpine

ENV ENVIRONMENT=default

LABEL author="FrancoisGib"
EXPOSE 8081
EXPOSE 5672

COPY user-service/target/*.jar user-service.jar
ENTRYPOINT java -jar -Dspring.profiles.active=$ENVIRONMENT user-service.jar