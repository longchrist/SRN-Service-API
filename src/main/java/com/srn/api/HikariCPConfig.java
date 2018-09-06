package com.srn.api;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vikraa
 */

public class HikariCPConfig {

    /* //somehow unable to read from application properties
       //so i hardcoded below, should fix this issue
    @Value("${spring.datasource.hikari.driver-class-name}")
    private String driverClassName;
    
    @Value("${spring.datasource.jdbc.url}")
    private String jdbcurl;
    
    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int poolSize;*/

    public DataSource datasource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/sarirasa");
        ds.setUsername("sarirasa");
        ds.setPassword("123456");
        ds.setMaximumPoolSize(100);
        return ds;
    }
}
