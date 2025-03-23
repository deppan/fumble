package com.tsinsi.auth.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tsinsi.auth.application.port.out.repository",
        entityManagerFactoryRef = "sourceEntityManagerFactory",
        transactionManagerRef = "sourceTransactionManager"
)
public class SourceDataSourceConfiguration {

    @Primary
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties("spring.datasource.source")
    public HikariDataSource sourceDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "sourceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(sourceDataSource())
                .packages("com.tsinsi.auth.entity")
                .persistenceUnit("source")
                .build();
    }

    @Primary
    @Bean(name = "sourceTransactionManager")
    public PlatformTransactionManager sourceTransactionManager(@Qualifier("sourceDataSource") DataSource sourceDataSource) {
        return new DataSourceTransactionManager(sourceDataSource);
    }
}
