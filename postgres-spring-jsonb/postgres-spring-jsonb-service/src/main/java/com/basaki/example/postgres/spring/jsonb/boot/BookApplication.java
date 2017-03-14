package com.basaki.example.postgres.spring.jsonb.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * {@code BookApplication} represents the entry point for book controller
 * spring boot application.
 * <p/>
 *
 * @author Indra Basak
 * @since 2/23/17
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.basaki.example.postgres.spring.jsonb.config",
        "com.basaki.example.postgres.spring.jsonb.controller",
        "com.basaki.example.postgres.spring.jsonb.data.entity",
        "com.basaki.example.postgres.spring.jsonb.data.repository",
        "com.basaki.example.postgres.spring.jsonb.error",
        "com.basaki.example.postgres.spring.jsonb.model",
        "com.basaki.example.postgres.spring.jsonb.service"})
@EntityScan(basePackages = "com.basaki.example.postgres.spring.jsonb.data.entity")
@EnableJpaRepositories(basePackages = {"com.basaki.example.postgres.spring.jsonb.data.repository"})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
