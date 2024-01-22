package com.todo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/project")
                .allowedOrigins("https://rbettarelli.github.io")
                // Adicione outras configurações CORS conforme necessário
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}