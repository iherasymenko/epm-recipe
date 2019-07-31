package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.DBRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new DBRecipeRepository();
    }
}
