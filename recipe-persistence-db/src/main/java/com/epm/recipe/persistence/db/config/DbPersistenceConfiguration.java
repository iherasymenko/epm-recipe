package com.epm.recipe.persistence.db.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.db.dao.DbPersistenceRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new DbPersistenceRepository();
    }

}
