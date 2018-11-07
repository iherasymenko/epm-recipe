package com.epm.recipe.persistence.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariDataSourceConfiguration {
    @Bean
    public HikariConfig hikariConfig() {

        HikariConfig config = new HikariConfig();
        config.setPoolName("hikariCp");
        config.setConnectionTestQuery("select 1");
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(60000);
        config.addDataSourceProperty("url", "jdbc:h2:mem:recipe;create=true");
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "");
        return config;
    }

    @Bean
    public HikariDataSource dataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

}
