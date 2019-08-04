package com.epm.recipe.aggregator.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertyPlaceholderConfigurerConfig {

    @Bean
    public static PropertyPlaceholderConfigurer ppc() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new FileSystemResource("conf/application.properties"));
        ppc.setSearchSystemEnvironment(true);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
