package com.srn.api;

import com.srn.api.service.impl.SrnDeviceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author long
 * Application Main Class Spring Boot
 */

@SpringBootApplication
@EnableJpaRepositories("com.srn.api.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource hikariCPConfig() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
            
        return ds;
    }
        
}
