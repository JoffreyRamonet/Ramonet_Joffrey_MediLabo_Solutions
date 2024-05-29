FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/configuration_server-0.0.1-SNAPSHOT.jar configuration_server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "configuration_server-0.0.1-SNAPSHOT.jar"]
EXPOSE 9101