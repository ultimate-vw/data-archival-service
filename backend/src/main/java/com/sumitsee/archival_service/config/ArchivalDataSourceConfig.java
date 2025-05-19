package com.sumitsee.archival_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;

import java.util.Objects;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "custom.datasource.archival")
@EnableJpaRepositories(
        basePackages = { "com.sumitsee.archival_service.repository.archival",
        "com.sumitsee.archival_service.repository.config",
        "com.sumitsee.archival_service.repository.security"
        },
        entityManagerFactoryRef = "archivalEntityManagerFactory",
        transactionManagerRef = "archivalTransactionManager"
)
public class ArchivalDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("custom.datasource.archival")
    public DataSourceProperties archivalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource archivalDataSource() {
        return archivalDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "archivalEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean archivalEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("archivalDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.sumitsee.archival_service.entity.archival",
                        "com.sumitsee.archival_service.entity.security",
                        "com.sumitsee.archival_service.entity.config"     // <- For ArchivalConfig
                )
                .persistenceUnit("archival")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager archivalTransactionManager(
            @Qualifier("archivalEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
        return new JpaTransactionManager(Objects.requireNonNull(factory.getObject()));
    }

//    @Bean(name = "securityEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("archivalDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.sumitsee.archival_service.entity.security")
//                .persistenceUnit("security")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager securityTransactionManager(
//            @Qualifier("securityEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
//        return new JpaTransactionManager(factory.getObject());
//    }

}
