package com.epm.recipe.persistence.jdbc.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariConnectionPool implements ConnectionProducer{

    private HikariConfig config;
    private HikariDataSource ds;

    public HikariConnectionPool() {
        config = new HikariConfig();
        setProperties();
        ds = new HikariDataSource(config);
    }

    private void setProperties(){
        try {
            Properties property = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            if (inputStream == null) {
                throw new IOException();
            }
            property.load(inputStream);
            config.setJdbcUrl(property.getProperty("jdbcUrl"));
            config.setUsername(property.getProperty("dataSource.user"));
            config.setPassword(property.getProperty("dataSource.password"));
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.addDataSourceProperty("cachePrepStmts", true);
            config.addDataSourceProperty("prepStmtCacheSize", 256);
            config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            config.addDataSourceProperty("useServerPrepStmts", true);
        } catch (IOException ex){
            throw new UncheckedIOException("can not load properties file: db.properties", ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
