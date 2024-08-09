package com.example.ecommerce.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("http://localhost:3000")
          .allowedHeaders("Content-Type", "x-requested-with", "accept", "origin", "authorization", "x-csrftoken", "x-xsrf-token")
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPITIONS", "PATCH")
          .allowCredentials(true);
    }
}
