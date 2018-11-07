package com.epm.recipe.persistence.in_memory.config;


import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {

    public void prepareDb() throws SQLException, IOException {
        try {
            Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Connection connection = getDataSource().getConnection();


        Reader reader = getResource("recipes.sql");
        RunScript.execute(connection, reader);
        reader = getResource("addRecipes.sql");
        RunScript.execute(connection, reader);

    }


    public DataSource getDataSource() throws IOException {
        Reader inputStreamReader = getResource("db.properties");
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(properties.getProperty("url"));
        driverManagerDataSource.setPassword(properties.getProperty("username"));
        driverManagerDataSource.setUsername(properties.getProperty("password"));
        return driverManagerDataSource;
    }

    private Reader getResource(String resource) {
        return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
    }
}