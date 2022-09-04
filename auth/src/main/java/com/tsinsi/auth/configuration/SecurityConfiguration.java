package com.tsinsi.auth.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http, @NotNull Whitelist whitelist) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http.exceptionHandling()
                .authenticationEntryPoint(new AppAuthenticationEntryPoint())
                .accessDeniedHandler(new AppAccessDeniedHandler())
                .and();

        http.authorizeHttpRequests((authorize) ->
                authorize.antMatchers(whitelist.getPaths())
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
