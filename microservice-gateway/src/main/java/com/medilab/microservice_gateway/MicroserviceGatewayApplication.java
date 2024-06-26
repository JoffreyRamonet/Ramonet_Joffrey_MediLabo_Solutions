package com.medilab.microservice_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Class Run of the microservice.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MicroserviceGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayApplication.class, args);
    }
}
