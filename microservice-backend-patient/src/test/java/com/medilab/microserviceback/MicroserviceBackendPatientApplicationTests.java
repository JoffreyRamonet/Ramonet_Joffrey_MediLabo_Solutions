package com.medilab.microserviceback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
class MicroserviceBackendPatientApplicationTests {

	@Test
	void contextLoads() {
	}

}
