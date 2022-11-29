package com.tsinsi.auth.configuration;

import com.tsinsi.auth.application.port.out.AccountRepository;
import com.tsinsi.auth.entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository repository) {
        return username -> {
            Account account = repository.findByUsername(username);
            return User.withUsername(String.valueOf(account.getId()))
                    .password(account.getPassword())
                    .authorities(Collections.emptyList())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        };
    }

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper();
    }
}
