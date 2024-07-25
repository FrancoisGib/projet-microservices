FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY logs-service/target/lib lib
COPY logs-service/target/*.jar logs-service.jar

ENV ENVIRONMENT=default

EXPOSE 5672
ENTRYPOINT ["/jre/bin/java", "-jar", "logs-service.jar"]