package com.medilab.microserviceback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Class Run of the microservice.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
public class MicroserviceBackendPatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBackendPatientApplication.class, args);
	}

}
