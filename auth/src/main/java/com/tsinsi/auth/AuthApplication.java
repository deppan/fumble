package com.tsinsi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication(scanBasePackages = {"com.tsinsi.configuration", "com.tsinsi.auth"})
public class AuthApplication implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    public AuthApplication(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
