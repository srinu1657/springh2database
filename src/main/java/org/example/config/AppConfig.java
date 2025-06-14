package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.example.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.maxConnections}")
    private int maxConnections;

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaximumPoolSize(maxConnections); // max 5 connections
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public EmployeeDao employeeDao(JdbcTemplate jdbcTemplate) {
        return new EmployeeDao(jdbcTemplate);
    }

}
