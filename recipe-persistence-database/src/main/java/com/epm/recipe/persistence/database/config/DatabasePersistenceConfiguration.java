package com.epm.recipe.persistence.database.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.database.DatabaseRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabasePersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new DatabaseRecipeRepository();
    }

}
