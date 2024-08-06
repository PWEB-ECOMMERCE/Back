package com.example.ecommerce.filter;

import com.example.ecommerce.controllers.UsuarioController;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<Filtro> registrationBean(){
        FilterRegistrationBean<Filtro> register = new FilterRegistrationBean<>();

        register.setFilter(new Filtro());
        //isso configura o filtro para que atue sobre a url abaixo, ou seja, /usuarios e todas as variações
        register.addUrlPatterns("/usuarios/desabilitando");
        return register;
    }
}
