package com.tsinsi.auth.configuration;

import com.tsinsi.auth.configuration.util.AppAccessDeniedHandler;
import com.tsinsi.auth.configuration.util.FailureAuthenticationEntryPoint;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http, @NotNull Whitelist whitelist) throws Exception {
        http = http.csrf().disable();
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http.exceptionHandling()
                .authenticationEntryPoint(new FailureAuthenticationEntryPoint())
                .accessDeniedHandler(new AppAccessDeniedHandler())
                .and();

        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers(whitelist.getPaths())
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );

        return http.build();
    }
}
