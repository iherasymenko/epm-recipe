package com.epm.recipe.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertySourcesConfiguration {

    @Bean
    public static PropertyPlaceholderConfigurer placeholder(){
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new FileSystemResource("conf/db.properties"));
        return placeholderConfigurer;
    }

}
