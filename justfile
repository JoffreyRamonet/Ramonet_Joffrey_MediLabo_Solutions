alias dd := docker-down-clean
alias dr := docker-run
alias p := packages

#default run
default:
    just --list

# Package the microservice-gateway and build the docker.
package-microservice-gateway:
    cd microservice-gateway && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-gateway .

# Package the microservice-front and build the docker.
package-microservice-front:
    cd microservice-front && docker build -t medilabo-microservice-front .

# Package the microservice-eureka-server and build the docker.
package-microservice-eureka-server:
    cd microservice-eureka-server && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-eureka-server .

# Package the microservice-configuration-server and build the docker.
package-microservice-configuration-server:
    cd microservice-configuration-server && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-configuration-server .

# Package the microservice-backend-patient and build the docker.
package-microservice-backend-patient:
    cd microservice-backend-patient && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-patient .

# Package the microservice-backend-note and build the docker.
package-microservice-backend-note:
    cd microservice-backend-note && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-note .

# Package the microservice-backend-assessor and build the docker.
package-microservice-backend-assessor:
    cd microservice-backend-assessor && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-assessor .

# Package all microservices and build dockers.
packages:
    cd microservice-gateway && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-gateway .
    cd microservice-front && docker build -t medilabo-microservice-front .
    cd microservice-eureka-server && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-eureka-server .
    cd microservice-configuration-server && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-configuration-server .
    cd microservice-backend-patient && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-patient .
    cd microservice-backend-note && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-note .
    cd microservice-backend-assessor && mvn clean package -Dmaven.test.skip=true && docker build -t medilabo-microservice-backend-assessor .

# Run the databases and keycloak dockers for local running.
basic-docker-run:
    docker compose up keycloak-mysql microservice-back-mysql microservice-back-mongodb keycloak

# Run the docker-compose.
docker-run:
    docker compose up

# Remove containers, images and volumes of the previous docker-compose run.
docker-down-clean:
    docker compose down --rmi all --volumes --remove-orphans