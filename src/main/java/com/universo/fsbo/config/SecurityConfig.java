package com.universo.fsbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Deshabilitar proteccion CSRF 
		http.csrf((csrf) -> csrf.disable());
		
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/property/**").permitAll() // permite el endpoint sin auth
                .anyRequest().authenticated() // resto requiere login si lo añades en el futuro
            )
            //.oauth2Login(Customizer.withDefaults()); // Enables OAuth2 Login
        ;
        
        return http.build();
        
    }
    
}
