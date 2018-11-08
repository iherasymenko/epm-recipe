package com.epm.recipe.persistence.db.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true)
class DbPersistenceConfiguration {

    private final Environment environment;

    @Autowired
    public DbPersistenceConfiguration(Environment environment) {
        this.environment = Objects.requireNonNull(environment, "environment");
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);

        return dataSource;
    }

    private DriverManagerDataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("database.driver"));
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.user"));
        dataSource.setPassword(environment.getProperty("database.password"));

        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        return new ResourceDatabasePopulator(
                new ClassPathResource("recipe-schema.sql"),
                new ClassPathResource("recipes.sql")
        );
    }
}
