package com.devweb.plocadora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Permite qualquer origem
                .allowedMethods("*") // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true) // Permite envio de credentials (cookies, authorization headers)
                .maxAge(3600); // Cache de preflight por 1 hora
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // Permite qualquer origem
        configuration.addAllowedMethod("*"); // Permite todos os métodos
        configuration.addAllowedHeader("*"); // Permite todos os headers
        configuration.setAllowCredentials(true); // Permite credentials
        configuration.setMaxAge(3600L); // Cache de preflight

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}