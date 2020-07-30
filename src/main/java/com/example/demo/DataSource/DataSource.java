package com.example.demo.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.ConstructorProperties;

@Configuration
public class DataSource {


    @Bean
    @ConfigurationProperties("app.datasource")
    public  HikariDataSource Hikari()
    {
       return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
