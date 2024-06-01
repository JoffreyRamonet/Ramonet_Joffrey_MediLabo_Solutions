FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/microservice-backend_note-0.0.1-SNAPSHOT.jar microservice-backend_note-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-backend_note-0.0.1-SNAPSHOT.jar"]
EXPOSE 9003
