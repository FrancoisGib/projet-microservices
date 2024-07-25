FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY user-service/target/*.jar user-service.jar

ENV ENVIRONMENT=default

EXPOSE 8082
EXPOSE 5672
ENTRYPOINT /jre/bin/java -jar -Dspring.profiles.active=$ENVIRONMENT user-service.jar