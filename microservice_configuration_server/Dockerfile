FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/microservice-configuration-server-0.0.1-SNAPSHOT.jar microservice-configuration-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-configuration-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 9101