package com.srn.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${hibernate.jdbc.batch.size}")
    private int hibernateJdbcBatchSize;

    @Value("${hibernate.order.inserts}")
    boolean hibernateOrderInserts;

    @Value("${hibernate.order.updates}")
    boolean hibernateOrderUpdates;

    @Value("${spring.datasource.hikari.driver-class-name}")
    String hikariDriverClassName;

    @Value("${spring.datasource.hikari.jdbc-url}")
    String hikariJdbcUrl;

    @Value("${spring.datasource.hikari.username}")
    String hikariJdbcUserName;

    @Value("${spring.datasource.hikari.password}")
    String hikariJdbcPassword;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    int hikariJdbcMaximumPool;

    @Value("${spring.datasource.hikari.connection-timeout}")
    long hikariJdbcConnectionTimeout;


    public static void main(String[] args) {
        SpringApplication.run(SrnApplication.class, args);
    }

    @Bean
    public DataSource hikariCPConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(hikariDriverClassName);
        config.setJdbcUrl(hikariJdbcUrl);
        config.setUsername(hikariJdbcUserName);
        config.setPassword(hikariJdbcPassword);
        config.setMaximumPoolSize(hikariJdbcMaximumPool);
        config.setConnectionTimeout(hikariJdbcConnectionTimeout);

        HikariDataSource ds = new HikariDataSource(config);
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
        jpaProperties.put("hibernate.jdbc.batch_size", hibernateJdbcBatchSize);
        jpaProperties.put("hibernate.order_inserts", hibernateOrderInserts);
        jpaProperties.put("hibernate.order_updates", hibernateOrderUpdates);
        factory.setJpaProperties(jpaProperties);

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.srn.api.model.entity");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}