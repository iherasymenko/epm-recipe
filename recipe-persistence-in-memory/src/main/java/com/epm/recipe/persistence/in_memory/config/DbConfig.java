package com.epm.recipe.persistence.in_memory.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DbConfig {

    private static final String DB_PROPERTIES = "recipe-db.properties";

    private final Properties properties;

    DbConfig() {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(DB_PROPERTIES)) {
            this.properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Could not load database properties");
        }
    }

    String driver() {
        return properties.getProperty(Props.DRIVER.getKey());
    }

    String url() {
        return properties.getProperty(Props.URL.getKey());
    }

    String user() {
        return properties.getProperty(Props.USER.getKey());
    }

    String password() {
        return properties.getProperty(Props.PASSWORD.getKey());
    }


    enum Props {
        DRIVER("driver"),
        URL("url"),
        USER("username"),
        PASSWORD("password");

        private String key;

        Props(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}