FROM minimified-jre
LABEL AUTHOR="FrancoisGib"

WORKDIR /app
COPY kube-service/target/*.jar kube-service.jar

ENV ENVIRONMENT=default

EXPOSE 8083
EXPOSE 5672
ENTRYPOINT /jre/bin/java -jar -Dspring.profiles.active=$ENVIRONMENT kube-service.jar