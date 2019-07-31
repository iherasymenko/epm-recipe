package com.epm.recipe.persistence.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static final String PROPERTY_FILE = "db.properties";

    public static Connection getConnection() throws SQLException {
        Properties property = new Properties();

        String url = null;
        String username = null;
        String password = null;
        String classname;

        try (InputStream inputStream = DBManager.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            property.load(inputStream);

            url = property.getProperty("url");
            username = property.getProperty("user");
            password = property.getProperty("password");
            classname = property.getProperty("driver");
            Class.forName(classname);
        } catch (IOException e) {
            throw new IllegalStateException("Property file not found!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }
}
