package com.example.umc_9th_chiki.config;

import com.querydsl.jpa.impl.JPAQueryFactory;

// 2. JPA(Jakarta Persistence) 관련 import
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// 3. Spring 관련 import
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}