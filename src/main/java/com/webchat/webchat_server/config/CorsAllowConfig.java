package com.webchat.webchat_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsAllowConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registery){
        registery.addMapping("/**")
        .allowedOriginPatterns("http://localhost:5173")
        .allowedMethods("GET", "POST", "PUT","DELETE","OPTIONS")
        .allowCredentials(true)
        .allowedHeaders("*");
    }
}
