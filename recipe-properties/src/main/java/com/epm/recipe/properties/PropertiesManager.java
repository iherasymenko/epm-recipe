package com.epm.recipe.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesManager {

    private final static Logger log = LoggerFactory.getLogger(PropertiesManager.class);
    private Properties properties;

    @Bean
    public Properties getDBProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) {
                properties.load(stream);
            } catch (IOException e) {
                log.error("Failed to load property file", e);
            }
        }
        return properties;
    }

}
