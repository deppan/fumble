package com.tsinsi.gateway.configuration;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfiguration {

    @Value("${hashids.salt}")
    private String salt = "";

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper();
    }

    @Bean
    public Hashids hashids() {
        return new Hashids(salt);
    }

}
