package com.epm.recipe.persistence.db.config;

import com.epm.recipe.persistence.db.RecipeRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@Import(JdbcConfiguration.class)
public class RecipeDaoImplConfiguration {

    @Bean
    public RecipeRepositoryImpl recipeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new RecipeRepositoryImpl(jdbcTemplate);
    }
}
