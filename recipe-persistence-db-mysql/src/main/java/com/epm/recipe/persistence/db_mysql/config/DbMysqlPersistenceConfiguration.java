package com.epm.recipe.persistence.db_mysql.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.db_mysql.DbMysqlRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbMysqlPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new DbMysqlRecipeRepository();
    }
}
