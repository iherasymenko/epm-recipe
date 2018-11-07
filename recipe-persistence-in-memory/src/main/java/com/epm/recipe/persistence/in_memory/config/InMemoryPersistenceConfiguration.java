package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.in_memory.InMemoryRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class InMemoryPersistenceConfiguration {

    @Bean
    public DataSource dataSource() throws IOException, SQLException {
        DbManager dbManager = new DbManager();
        dbManager.prepareDb();
        return dbManager.getDataSource();
    }

    @Bean
    public InMemoryRecipeRepository recipeRepository(DataSource dataSource) {
        InMemoryRecipeRepository inMemoryRecipeRepository = new InMemoryRecipeRepository();
        inMemoryRecipeRepository.setDataSource(dataSource);
        return inMemoryRecipeRepository;
    }

}
