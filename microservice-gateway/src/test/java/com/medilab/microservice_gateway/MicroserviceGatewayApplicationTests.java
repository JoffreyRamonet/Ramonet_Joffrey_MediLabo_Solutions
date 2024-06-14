package com.medilab.microservice_gateway;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RunWith(SpringRunner.class)
class MicroserviceGatewayApplicationTests {

	@Test
	void contextLoads() {
	}

}
