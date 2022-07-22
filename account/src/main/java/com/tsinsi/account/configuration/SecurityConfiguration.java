package com.tsinsi.account.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsinsi.account.configuration.util.ClaimSet;
import jakarta.validation.constraints.NotNull;
import jodd.net.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.util.Map;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

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

        http.formLogin()
                .successHandler((request, response, authentication) -> {
                    String name = authentication.getName();
                    ClaimSet claimSet = new ClaimSet();
                    claimSet.setDays(3);
                    claimSet.setUsername(name);
                    claimSet.setUid(Long.parseLong(name));

                    String token = jwtHelper.generate(claimSet);

                    response.addHeader(HttpHeaders.AUTHORIZATION, token);
                    response.setStatus(HttpStatus.ok().status());
                    Map<String, Object> map = Map.of("message", "Success");
                    PrintWriter writer = response.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(map));
                    writer.flush();
                    writer.close();
                })
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and().successHandler(((request, response, authentication) -> {
                    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                }))
        ;

        http.authorizeHttpRequests((authorize) ->
                authorize.antMatchers(whitelist.getPaths())
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );

        http.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
