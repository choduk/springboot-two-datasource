package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.example.demo.GreenDataBaseConfig.GREEN_ENTITY_BASE_PACKAGES;

/**
 * @author choduk88@sk.com
 * @since 2017. 7. 4..
 */
@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = {GREEN_ENTITY_BASE_PACKAGES},
        entityManagerFactoryRef = "greenEntityManagerFactory",
        transactionManagerRef = "greenTransactionManager"
)
public class GreenDataBaseConfig {

    public static final String GREEN_ENTITY_BASE_PACKAGES = "com.example.demo.green";

    @Bean
    @ConfigurationProperties("app.datasource.green")
    public DataSourceProperties greenDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    public DataSource greenDataSource() {
        DataSourceProperties dataSourceProperties = greenDataSourceProperties();
        log.warn("green dataSource url : {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean greenEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(greenDataSource())
                .packages(GREEN_ENTITY_BASE_PACKAGES)
                .persistenceUnit("green")
                .build();
    }

    @Bean
    public PlatformTransactionManager greenTransactionManager(@Qualifier("greenEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
