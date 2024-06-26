FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/microservice-eureka-server-0.0.1-SNAPSHOT.jar microservice-eureka-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-eureka-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 9102