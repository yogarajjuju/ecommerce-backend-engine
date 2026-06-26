package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 📦 NEW IMPORTS FOR CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. ⬅️ THE FIX: Turn off the Forcefield so the Frontend can connect!
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                // ✨ THE FIX: Always allow the browser's secret "OPTIONS" preflight pings!
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                
                .requestMatchers("/login", "/register", "/status").permitAll()
                .requestMatchers("/buy", "/test-refund").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 2. ⬅️ THE CORS RULES: Telling the Bouncer who is allowed to talk to the server
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow any frontend address (Codespaces, localhost:5500, etc.)
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); 
        
        // Allow standard web actions
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        
        // ✨ CRITICAL: Explicitly allow the Bouncer to read the "Authorization" header
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}