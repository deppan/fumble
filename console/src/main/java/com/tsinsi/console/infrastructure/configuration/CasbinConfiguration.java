package com.tsinsi.console.infrastructure.configuration;

import org.casbin.adapter.JDBCAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.Adapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class CasbinConfiguration {

    @Bean
    public Adapter casbinAdapter(DataSource sourceDataSource) throws Exception {
        return new JDBCAdapter(sourceDataSource);
    }

    @Bean
    public Enforcer enforcer(Adapter adapter) throws Exception {
        Model model = new Model();
        try (InputStream is = new ClassPathResource("rbac_model.conf").getInputStream()) {
            String modelText = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            model.loadModelFromText(modelText);
        }
        Enforcer enforcer = new Enforcer(model, adapter);
        enforcer.loadPolicy();
        return enforcer;
    }
}
