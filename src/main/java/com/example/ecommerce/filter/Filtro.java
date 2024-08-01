package com.example.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class Filtro implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        //isso é o filtro para que o usuario tenha uma sessao válida para poder acessar o que esteja protegido

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        if (session != null && session.getAttribute("usuario") != null){
            System.out.println("Voce tem permissao");
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).setStatus(500);
            System.out.println("Você NÃO tem permissão!");
            servletRequest.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
        }
    }
}
