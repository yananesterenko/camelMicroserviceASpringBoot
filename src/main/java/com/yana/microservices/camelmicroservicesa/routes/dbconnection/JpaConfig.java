package com.yana.microservices.camelmicroservicesa.routes.dbconnection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Configuration
@Component
public class JpaConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary

    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}