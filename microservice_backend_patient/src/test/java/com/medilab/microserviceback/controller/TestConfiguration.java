package com.medilab.microserviceback.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"com.medilab.microserviceback.repository", "com.medilab.microserviceback.service"}
        )
public class TestConfiguration {
}
