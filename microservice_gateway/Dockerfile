FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y net-tools
COPY /target/microservice-gateway-0.0.1-SNAPSHOT.jar microservice-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-gateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 9000
