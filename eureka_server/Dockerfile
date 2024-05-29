FROM eclipse-temurin:latest
RUN apt-get update && apt-get install -y curl
COPY /target/eureka-0.0.1-SNAPSHOT.jar eureka-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]
EXPOSE 9102