package com.example.Myspring_homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Bean
    public MyEnvironment MyEnvironment() {
        return MyEnvironment.builder()
                .mode("개발환경")
                .rate(0.5)
                .build();
    }
}