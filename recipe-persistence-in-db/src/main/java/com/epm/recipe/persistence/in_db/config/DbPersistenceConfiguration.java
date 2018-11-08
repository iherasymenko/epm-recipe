package com.epm.recipe.persistence.in_db.config;

import com.epm.recipe.persistence.in_db.DbRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbPersistenceConfiguration {

    private final String DRIVER_CLASS = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:tcp://localhost/mem:test;DB_CLOSE_DELAY=-1;create=true;DATABASE_TO_UPPER=false";
    private final String USER = "sa";
    private final String PASSWORD = "";

    @Bean
    public DbRecipeRepository recipeRepository() {
        return new DbRecipeRepository();
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }
}