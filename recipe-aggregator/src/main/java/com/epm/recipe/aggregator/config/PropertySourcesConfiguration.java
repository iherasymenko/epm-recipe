package com.epm.recipe.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertySourcesConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholder(){
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        FileSystemResource fileSystemResource = new FileSystemResource("D:/epam/epm-recipe/recipe-aggregator/build/distributions/recipe-aggregator/recipe-aggregator/conf/db.properties.txt");
        placeholderConfigurer.setLocation(fileSystemResource);
        placeholderConfigurer.setIgnoreResourceNotFound(false);
        return placeholderConfigurer;
    }

}
