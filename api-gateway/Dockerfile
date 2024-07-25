FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY api-gateway/target/*.jar api-gateway.jar

ENV ENVIRONMENT=default

EXPOSE 9000
ENTRYPOINT /jre/bin/java -jar -Dspring.profiles.active=$ENVIRONMENT api-gateway.jar