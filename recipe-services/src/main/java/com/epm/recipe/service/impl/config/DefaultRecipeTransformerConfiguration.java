package com.epm.recipe.service.impl.config;

import com.epm.recipe.service.impl.DefaultRecipeTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRecipeTransformerConfiguration {
    @Bean
    public DefaultRecipeTransformer recipeConverter() {
        return new DefaultRecipeTransformer();
    }
}
