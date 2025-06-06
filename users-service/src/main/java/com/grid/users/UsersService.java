package com.grid.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UsersService {
    public static void main(String[] args) {
        SpringApplication.run(UsersService.class, args);
    }
}
