package com.medilab.microservice_backend_assessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Class Run of the microservice.
 */
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MicroserviceBackendAssessorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceBackendAssessorApplication.class, args);
    }
}
