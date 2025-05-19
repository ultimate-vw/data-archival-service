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

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "custom.datasource.archived")
@EnableJpaRepositories(
        basePackages = "com.sumitsee.archival_service.repository.archived",
        entityManagerFactoryRef = "archivedEntityManagerFactory",
        transactionManagerRef = "archivedTransactionManager"
)
public class ArchivedDataSourceConfig {

    @Bean
    @ConfigurationProperties("custom.datasource.archived")
    public DataSourceProperties archivedDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource archivedDataSource() {
        return archivedDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "archivedEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean archivedEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("archivedDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.sumitsee.archival_service.entity.archived")
                .persistenceUnit("archived")
                .build();
    }

    @Bean
    public PlatformTransactionManager archivedTransactionManager(
            @Qualifier("archivedEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
        return new JpaTransactionManager(factory.getObject());
    }
}
