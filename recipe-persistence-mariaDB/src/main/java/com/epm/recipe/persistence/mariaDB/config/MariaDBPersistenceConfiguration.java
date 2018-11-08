package com.epm.recipe.persistence.mariaDB.config;

import com.epm.recipe.persistence.mariaDB.MariaDBRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MariaDBPersistenceConfiguration {

    @Bean
    public MariaDBRecipeRepository recipeRepository() {
        return new MariaDBRecipeRepository();
    }
}
