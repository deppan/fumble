package com.tsinsi.auth.configuration;

import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.domain.persistence.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.sqids.Sqids;

import java.util.Collections;

@Configuration
public class AppConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserPersistencePort userPersistencePort) {
        return username -> {
            UserEntity userEntity = userPersistencePort.findByUsername(username);
            return User.withUsername(String.valueOf(userEntity.getId()))
                    .password(userEntity.getPassword())
                    .authorities(Collections.emptyList())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtHelper jwtHelper(Sqids sqids) {
        return new JwtHelper(sqids);
    }

}
