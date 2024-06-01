package com.medilab.microservice_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.applyPermitDefaultValues();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:4200/*"));
        corsConfig.setAllowedMethods(List.of("GET","POST","OPTIONS","DELETE", "PATCH"));
        corsConfig.setAllowedHeaders(List.of("*"));
        
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(source -> corsConfig))
                .authorizeExchange(exchange -> exchange.anyExchange()
                        .authenticated())
                .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
                .build();
    }

}
