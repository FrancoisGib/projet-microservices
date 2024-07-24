FROM openjdk:17-alpine

ENV ENVIRONMENT=default

LABEL author="FrancoisGib"
EXPOSE 9000

COPY api-gateway/target/*.jar api-gateway.jar
ENTRYPOINT java -jar -Dspring.profiles.active=$ENVIRONMENT api-gateway.jar