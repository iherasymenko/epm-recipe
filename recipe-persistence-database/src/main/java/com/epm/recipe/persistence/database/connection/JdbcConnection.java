package com.epm.recipe.persistence.database.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {

    public static Connection getConnection() {

        Properties properties = new Properties();

        try (InputStream in = JdbcConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(in);
            String driver = properties.getProperty("jdbc.driver");
            if (driver != null) {
                Class.forName(driver);
            }
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.user");
            String password = properties.getProperty("jdbc.password");

            return DriverManager.getConnection(url, username, password);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception in JDBC connection " + e.getMessage());
        }
    }
}

