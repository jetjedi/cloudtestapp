package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "c.example.demo", "com.example.cloudtestapp" })
@EnableJpaRepositories(basePackages = "com.example.cloudtestapp.repository")
@EntityScan(basePackages = "com.example.cloudtestapp.model")
public class CloudTestAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudTestAppApplication.class, args);
    }
}