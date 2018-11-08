package com.epm.recipe.persistence.in_memory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private static final String PROPERTY_FILE_NAME = "db.properties";

    private Properties properties;

    public Properties getApplicationProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = getStreamOfResource(PROPERTY_FILE_NAME)) {
                properties.load(stream);
                Class.forName(properties.getProperty("driver"));
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed to load property file", e);
            }
        }
        return properties;
    }

    private static InputStream getStreamOfResource(String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }
}
