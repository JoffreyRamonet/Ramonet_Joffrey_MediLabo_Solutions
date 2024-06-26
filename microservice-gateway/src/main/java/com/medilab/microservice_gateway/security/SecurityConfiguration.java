package com.medilab.microservice_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * This the main security class.
 * <p>
 * Build the security web filter chain, essentially the CORS configuration.
 * Only the url of the microservice-frontend is allowed.
 * Only used methods are allowed.
 * </p>
 * <p>
 * This is a Bearer only authentication. The microservice-frontend parse a JWT into the header of each http request.
 * The Keycloak gateway client is called to authenticate the JWT.
 * </p>
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    
    /**
     * The Filter chain that block non-authorized http requests.
     * <p>
     * CORS configuration:
     * - required credentials
     * - allow only http requests from the microservice-frontend url.
     * - allow only methods required by a microservice-backend.
     * Oauth2server dependency id used to read the jwt.
     * </p>
     *
     * @param http - HttpSecurity.class, allows to apply the security filter string to HTTP requests.
     * @return a filter string configuration to apply.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.applyPermitDefaultValues();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:4200/*"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "DELETE", "PATCH"));
        corsConfig.setAllowedHeaders(List.of("*"));
        
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(source -> corsConfig))
                .authorizeExchange(exchange -> exchange.anyExchange()
                        .authenticated())
                .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
                .build();
    }
}