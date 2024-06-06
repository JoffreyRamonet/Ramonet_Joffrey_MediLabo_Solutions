/**
 * Microservice-gateway
 * This is the microservice that manages the security of the application's backend.
 * <p>
 * The application.properties is stored in an external repository managed by the microservice-configuration-server.
 * This makes it possible to modify the configuration without reboot the microservice.
 * DiscoveryService by the microservice-eureka
 * </p>
 */
module microservice.gateway {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.commons;
    requires spring.context;
    requires spring.security.config;
    requires spring.security.web;
    requires spring.web;
}