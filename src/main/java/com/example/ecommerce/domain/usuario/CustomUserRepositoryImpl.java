package com.example.ecommerce.domain.usuario;

import com.example.ecommerce.repository.CustomUserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManagerFactory entityManagerFactory;

    public CustomUserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
   public Usuario retornaUsuarioPorLogin(String login){
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       try{
           TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM usuario u WHERE u.login = ?1", Usuario.class);
           query.setParameter(1, login);
           return query.getSingleResult();
       } finally {
           entityManager.close();
       }


   }

}
