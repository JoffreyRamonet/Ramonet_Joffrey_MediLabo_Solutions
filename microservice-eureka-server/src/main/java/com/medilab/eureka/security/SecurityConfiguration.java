package com.medilab.eureka.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class to configure the security of the eureka service.
 */
@Configuration
public class SecurityConfiguration {
    
    @Value("${eureka.username}")
    private String username;
    @Value("${eureka.password}")
    private String password;
    
    /**
     * The Filter chain that block non-authorized http requests.
     * Forces all access requests to authenticate
     *
     * @param http - HttpSecurity.class, allows to apply the security filter string to HTTP requests.
     * @return a filter string configuration to apply.
     * @throws Exception cause of csrf
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
    /**
     * Juste one user is configured in memory.
     *
     * @return - UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        
        UserDetails ramesh = User.builder()
                .username("eureka")
                .password("{noop}password")
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(ramesh);
    }
}
