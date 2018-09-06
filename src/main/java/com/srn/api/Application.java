package com.srn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import javax.sql.DataSource;


/**
 * @author long
 * Application Main Class Spring Boot
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DataSource hikariCPConfig() {
		return new HikariCPConfig().datasource();
	}
}
