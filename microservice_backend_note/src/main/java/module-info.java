/**
 * Microservice-backend-note.
 * This is the microservice that manages all patient's note(s).
 * <p>
 * The application.properties is stored in an external repository managed by the microservice-configuration-server.
 * This makes it possible to modify the configuration without reboot the microservice.
 * DiscoveryService by the microservice-eureka
 * </p>
 */
module microservice.backend.note {
    requires jakarta.validation;
    requires static lombok;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.commons;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.mongodb;
    requires spring.web;
}