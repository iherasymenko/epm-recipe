package com.epm.recipe.persistence.db_mysql;

import com.epm.recipe.persistence.db_mysql.exception.DatabasePropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private String URL;
    private String USERNAME;
    private String PASSWORD;

    public ConnectionFactory() {
        Properties props = new Properties();
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("mysql.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new DatabasePropertyException("Can't load mysql.properties file", e);
        }

        URL = props.getProperty("db.url");
        USERNAME = props.getProperty("db.user");
        PASSWORD = props.getProperty("db.password");

        try {
            Class.forName(props.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new DatabasePropertyException("Can't load database driver", e);
        }
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
