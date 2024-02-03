package com.tsinsi.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.sqids.Sqids;

@Component
public class BeanConfiguration {

    @Value("${sqids.alphabet}")
    private String alphabet = "";

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper();
    }

    @Bean
    public Sqids hashids() {
        return Sqids.builder().minLength(12).alphabet(alphabet).build();
    }

}
