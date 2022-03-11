package com.tsinsi.fumble;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tsinsi.fumble.mapper")
@SpringBootApplication
public class FumbleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FumbleApplication.class, args);
    }

}
