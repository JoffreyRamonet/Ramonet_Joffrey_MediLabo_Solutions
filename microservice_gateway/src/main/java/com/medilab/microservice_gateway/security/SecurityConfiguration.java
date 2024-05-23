package com.medilab.microservice_gateway.security;



import org.springframework.boot.actuate.autoconfigure.condition.ConditionsReportEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ConditionsReportEndpoint conditionsReportEndpoint) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(source -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
                    configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                }))
                .authorizeExchange(exchange -> exchange.anyExchange()
                        .authenticated())
                .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
                .build();
    }
    

    
}
