FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY project-service/target/*.jar project-service.jar

ENV ENVIRONMENT=default

EXPOSE 8082
EXPOSE 5672
ENTRYPOINT /jre/bin/java -jar -Dspring.profiles.active=$ENVIRONMENT project-service.jar