package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example.demo",
        "controller",
        "service",
        "repository",
        "entity",
        "config"
})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "entity")
public class Umc9thApplication {
    public static void main(String[] args) {
        SpringApplication.run(Umc9thApplication.class, args);
    }
}
