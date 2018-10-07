package com.srn.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories("com.srn.api.repo")
@PropertySource("classpath:application.properties")
public class SrnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrnApplication.class, args);
    }

    @Bean
    public DataSource hikariCPConfig() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}