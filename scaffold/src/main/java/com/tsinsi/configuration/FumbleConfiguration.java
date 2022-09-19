package com.tsinsi.configuration;

import jakarta.validation.Validator;
import org.hashids.Hashids;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class FumbleConfiguration {

    @Value("${hashids.salt}")
    private String salt = "";

    private final MessageSource messageSource;

    public FumbleConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public Hashids hashids() {
        return new Hashids(salt);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {
                LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
                interceptor.setParamName("language");
                registry.addInterceptor(interceptor);
            }
        };
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator);
        return postProcessor;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        validator.setProviderClass(HibernateValidator.class);
        validator.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        return validator;
    }
}
