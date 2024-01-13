package com.example.storeadmin.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.db")
@Configuration
@EnableJpaRepositories(basePackages = "com.example.db")
public class JpaConfig {
}
