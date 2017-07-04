package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.example.demo.BlueDataBaseConfig.BLUE_ENTITY_BASE_PACKAGES;

/**
 * @author choduk88@sk.com
 * @since 2017. 7. 4..
 */
@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = {BLUE_ENTITY_BASE_PACKAGES},
        entityManagerFactoryRef = "blueEntityManagerFactory",
        transactionManagerRef = "blueTransactionManager")
public class BlueDataBaseConfig {

    public static final String BLUE_ENTITY_BASE_PACKAGES = "com.example.demo.blue";

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.blue")
    public DataSourceProperties blueDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource blueDataSource() {
        DataSourceProperties dataSourceProperties = blueDataSourceProperties();
        log.warn("green dataSource url : {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean blueEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(blueDataSource())
                .packages(BLUE_ENTITY_BASE_PACKAGES)
                .persistenceUnit("blue")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager blueTransactionManager(@Qualifier("blueEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
