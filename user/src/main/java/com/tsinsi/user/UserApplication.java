package com.tsinsi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(scanBasePackages = {"com.tsinsi.foundation", "com.tsinsi.user"})
@EnableRedisRepositories(basePackages = "com.tsinsi.user.domain.persistence.nosql")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
