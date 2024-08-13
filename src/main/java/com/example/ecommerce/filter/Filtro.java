package com.example.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Order(2)
public class Filtro extends OncePerRequestFilter {
    // @Override
    // public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    //         throws IOException, ServletException {
    //
    //     //isso é o filtro para que o usuario tenha uma sessao válida para poder acessar o que esteja protegido
    //
    //     HttpServletRequest req = (HttpServletRequest) servletRequest;
    //     HttpSession session = ((HttpServletRequest) req).getSession(false);
    //     if (session != null && session.getAttribute("usuario") != null){
    //         System.out.println("Voce tem permissao");
    //         filterChain.doFilter(servletRequest, servletResponse);
    //     }else{
    //         ((HttpServletResponse) servletResponse).setStatus(500);
    //         System.out.println("Você NÃO tem permissão!");
    //         servletRequest.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
    //     }
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Handle preflight requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (session != null && session.getAttribute("usuario") != null){
            System.out.println("Voce tem permissao");
            filterChain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).setStatus(500);
            System.out.println("Você NÃO tem permissão!");
            request.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if ("/login".equals(path)) {
            return true;
        }

        if ("/usuarios".equals(path) && "POST".equalsIgnoreCase(method) || "/usuarios".equals(path) && "GET".equalsIgnoreCase(method)){
            return true;
        }

        return false;
    }
}
