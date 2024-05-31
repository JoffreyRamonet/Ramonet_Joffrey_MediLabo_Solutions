package com.medilab.microserviceback;

import com.medilab.microserviceback.controller.TestConfiguration;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class MicroserviceBackendPatientApplicationTests {

	@Test
	void contextLoads() {
	}

}
