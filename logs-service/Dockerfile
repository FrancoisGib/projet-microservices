FROM openjdk:17-alpine

LABEL author="FrancoisGib"
EXPOSE 5672

COPY logs-service/target/lib lib
COPY logs-service/target/*.jar logs-service.jar
ENTRYPOINT ["java", "-jar", "logs-service.jar"]