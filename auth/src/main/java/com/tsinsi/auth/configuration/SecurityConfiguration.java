package com.tsinsi.auth.configuration;

import com.tsinsi.auth.configuration.util.AppAccessDeniedHandler;
import com.tsinsi.auth.configuration.util.FailureAuthenticationEntryPoint;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "whitelist")
public class SecurityConfiguration {

    private String[] paths = null;

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(new FailureAuthenticationEntryPoint())
                                .accessDeniedHandler(new AppAccessDeniedHandler()))
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(Optional.ofNullable(paths).orElse(new String[0]))
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );
        return http.build();
    }
}
