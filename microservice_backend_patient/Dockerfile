FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/microservice_backend_patient-0.0.1-SNAPSHOT.jar microservice_backend_patient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice_backend_patient-0.0.1-SNAPSHOT.jar"]
EXPOSE 9001
