package com.epm.recipe.persistence.in_db;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class Service {
    private static final String DB_PATH = "db.properties";
    /**
     * Gets a connection from the properties specified in the file
     * db.properties @return the database connection
     */
    static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in =
                     Service.class.getClassLoader().getResourceAsStream(DB_PATH)) {
            props.load(in);
        }
        String drivers = props.getProperty("driver");
        if (drivers != null) {
            System.setProperty("driver", drivers);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

}
