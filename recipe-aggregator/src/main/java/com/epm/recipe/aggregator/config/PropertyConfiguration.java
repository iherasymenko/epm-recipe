package com.epm.recipe.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertyConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        FileSystemResource fileSystemResource = new FileSystemResource("conf/db.properties");
        configurer.setLocation(fileSystemResource);
        configurer.setIgnoreResourceNotFound(true);
        return configurer;
    }
}
