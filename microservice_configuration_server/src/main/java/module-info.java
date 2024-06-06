/**
 * Microservice_configuration_server
 * This microservice manages all microservices's application.properties.
 * <p>
 * All files of configuration are store in another distant repository.
 * </p>
 */
module microservice.configuration.server {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.config.server;
}