package com.epm.recipe.aggregator.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class AggregatorConfiguration {

    // TODO: Can't find place where I can start application and put to this place app.properties
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc
                = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new FileSystemResource("app.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
