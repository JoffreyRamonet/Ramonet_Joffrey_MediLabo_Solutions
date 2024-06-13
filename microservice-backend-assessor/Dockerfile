FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/microservice-backend-assessor-0.0.1-SNAPSHOT.jar microservice-backend-assessor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-backend-assessor-0.0.1-SNAPSHOT.jar"]

