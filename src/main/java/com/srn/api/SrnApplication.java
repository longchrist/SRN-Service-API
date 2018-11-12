package com.srn.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

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
        ds.setMaximumPoolSize(1000);
        return ds;
    }

    @Bean
    public EmbeddedServletContainerCustomizer tomcatConfig() {
        return new TomcatConfig();
    }


    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        //Use these properties to let spring work on batch insertion
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.jdbc.batch_size", 500);
        jpaProperties.put("hibernate.order_inserts", true);
        jpaProperties.put("hibernate.order_updates", true);
        factory.setJpaProperties(jpaProperties);

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.srn.api.model.entity");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}