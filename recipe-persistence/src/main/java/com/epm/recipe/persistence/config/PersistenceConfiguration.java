package com.epm.recipe.persistence.config;

import com.epm.recipe.persistence.JdbcTemplateRecipeRepository;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new JdbcTemplateRecipeRepository(dataSource());
    }

    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:~/sampledb");//"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource());
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSeparator(";");
        populator.setCommentPrefix("--");
        populator.setContinueOnError(true);
        populator.addScript(new ClassPathResource("schema.sql"));
        return populator;
    }
}
