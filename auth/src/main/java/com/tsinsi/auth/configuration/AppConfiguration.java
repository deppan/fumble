package com.tsinsi.auth.configuration;

import com.tsinsi.auth.infrastructure.JwtHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqids.Sqids;

@Configuration
public class AppConfiguration {

    @Bean
    public JwtHelper jwtHelper(Sqids sqids) {
        return new JwtHelper(sqids);
    }

}
