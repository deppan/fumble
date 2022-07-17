package com.tsinsi.fumble.account.configuration;

import com.tsinsi.fumble.account.entity.Account;
import com.tsinsi.fumble.account.adapter.out.mapper.AccountRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BeanConfiguration {

    @Value("${hashids.salt}")
    private String salt = "";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository repository) {
        return username -> {
            Account account = repository.findByUsername(username);
            return User.withUsername(String.valueOf(account.getId()))
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

    @Bean
    public Hashids hashids() {
        return new Hashids(salt);
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

}
