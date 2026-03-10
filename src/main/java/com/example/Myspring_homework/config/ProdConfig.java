package com.example.Myspring_homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public MyEnvironment MyEnvironment() {
        return MyEnvironment.builder()
                .mode("운영환경")
                .rate(1.5)
                .build();
    }
}