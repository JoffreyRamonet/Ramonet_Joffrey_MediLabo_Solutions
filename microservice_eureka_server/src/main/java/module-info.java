/**
 * Microservice-eureka
 * This is the microservice that manages the discovery service.
 * <p>
 * The application.properties is stored in an external repository managed by the microservice-configuration-server.
 * This makes it possible to modify the configuration without reboot the microservice.
 * </p>
 */
module eureka {
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.netflix.eureka.server;
    requires spring.context;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.web;
}