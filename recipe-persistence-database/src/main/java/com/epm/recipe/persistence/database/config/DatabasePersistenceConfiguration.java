package com.epm.recipe.persistence.database.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.database.DatabaseRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabasePersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository(DataSource dataSource) {
        return new DatabaseRecipeRepository(dataSource);
    }

}
