package com.epm.recipe.converter.impl.config;

import com.epm.recipe.converter.impl.DefaultRecipeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRecipeConverterConfig {
    @Bean
    public DefaultRecipeConverter recipeConverter() {
        return new DefaultRecipeConverter();
    }
}
