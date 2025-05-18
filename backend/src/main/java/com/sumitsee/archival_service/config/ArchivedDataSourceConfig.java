package com.sumitsee.archival_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.sumitsee.archival_service.repository.archived",
        entityManagerFactoryRef = "archivedEntityManagerFactory",
        transactionManagerRef = "archivedTransactionManager"
)
public class ArchivedDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.archived")
    public DataSourceProperties archivedDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource archivedDatasource(){
        return archivedDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager archivedTransactionManager(
            final @Qualifier("archhivedEntityManagerFactory") LocalContainerEntityManagerFactoryBean archivedEntityManagerFactory
    ){
        return new JpaTransactionManager(archivedEntityManagerFactory.getObject());
    }
}
