FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY docker-service/target/*.jar docker-service.jar

ENV ENVIRONMENT=default

EXPOSE 8083
ENTRYPOINT /jre/bin/java -jar -Dspring.profiles.active=$ENVIRONMENT docker-service.jar