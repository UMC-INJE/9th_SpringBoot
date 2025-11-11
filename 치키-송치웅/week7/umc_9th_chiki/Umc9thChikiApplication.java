package com.example.umc_9th_chiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // 🚨 누락된 import 추가

@SpringBootApplication
@EnableJpaAuditing
public class Umc9thChikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc9thChikiApplication.class, args);
    }
}