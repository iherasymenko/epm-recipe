package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.properties.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DBConfiguration {
    private final static Logger log = LoggerFactory.getLogger(DBConfiguration.class);
    private PropertiesManager propertiesManager;

    public DBConfiguration(PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        try {
            Properties properties = propertiesManager.getDBProperties();

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Failed to open the DB connection", e);
        }
    }
}
