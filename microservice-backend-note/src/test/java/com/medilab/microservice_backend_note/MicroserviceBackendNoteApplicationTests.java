package com.medilab.microservice_backend_note;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
class MicroserviceBackendNoteApplicationTests {

	@Test
	void contextLoads() {
	}

}
