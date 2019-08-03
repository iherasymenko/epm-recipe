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
        FileSystemResource fileSystemResource = new FileSystemResource(System.getenv("recipe_aggregator_conf") + "db.properties");
        placeholderConfigurer.setLocation(fileSystemResource);
        placeholderConfigurer.setIgnoreResourceNotFound(false);
        return placeholderConfigurer;
    }

}
