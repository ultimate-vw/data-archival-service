package com.sumitsee.archival_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.sumitsee.archival_service.repository.archival",
        entityManagerFactoryRef = "archivalEntityManagerFactory",
        transactionManagerRef = "archivalTransactionManager"
)
public class ArchivalDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("custom.datasource.archival")
    public DataSourceProperties archivalDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource archivalDataSource(){
        return archivalDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager archivalTransactionManager(
        final @Qualifier("archivalEntityManagerFactory")LocalContainerEntityManagerFactoryBean archivalEntityManagerFactory)
    {
        return new JpaTransactionManager(archivalEntityManagerFactory.getObject());
    }

}
