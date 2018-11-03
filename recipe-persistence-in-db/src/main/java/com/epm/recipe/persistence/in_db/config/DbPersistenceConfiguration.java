package com.epm.recipe.persistence.in_db.config;

import com.epm.recipe.persistence.in_db.DbRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbPersistenceConfiguration {

    @Bean
    public DbRecipeRepository recipeRepository() { return new DbRecipeRepository();}
}
