package com.example.umc_9th_chiki.config;

// 1. Querydsl 관련 import
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

    @Bean // 👈 "이걸 Bean으로 설정!"
    public JPAQueryFactory jpaQueryFactory() {
        // 1. 앱 시작 시 딱 한 번만 "JPAQueryFactory(도구)"와
        //    "EntityManager(영속성 컨텍스트 관리자)"를 연동하여 생성
        return new JPAQueryFactory(entityManager);
    }
}