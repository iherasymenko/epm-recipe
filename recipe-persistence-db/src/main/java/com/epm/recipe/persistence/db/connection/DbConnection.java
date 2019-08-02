package com.epm.recipe.persistence.db.connection;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    // private static Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private static String db_driver;
    private static String db_url;
    private static String db_username;
    private static String db_password;

    private static boolean showVersions;

    public DbConnection() {
      //  loadProperties();
        if(db_driver == null || db_driver.length() == 0)
            db_driver = "com.mysql.cj.jdbc.Driver";
        if(db_url == null || db_url.length() == 0)
            db_url = "jdbc:mysql://localhost:3306/hw14?useUnicode=true&&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        if(db_username == null || db_username.length() == 0)
            db_username = "den";
        if(db_password == null || db_password.length() == 0)
            db_password = "den";
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            if(!showVersions)
                printVersions(connection);
        } catch (ClassNotFoundException | SQLException ex) {
           // logger.error("{}", ex);
            ex.printStackTrace();

        }
        return connection;
    }

    private void loadProperties() {
        try(FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            Properties property = new Properties();
            property.load(fis);

            db_driver = property.getProperty("driver");
            db_url = property.getProperty("url");
            db_username = property.getProperty("user");
            db_password = property.getProperty("password");

        } catch(IOException ex) {
        //    logger.error("{}", ex);
            System.out.println("Properties not found");
        }
    }

    public void printVersions(Connection connection) throws SQLException {
        /*
        logger.info("DB_version: {}", connection.getMetaData().getDatabaseProductVersion());
        logger.info("Driver version: {}", connection.getMetaData().getDriverVersion());
        logger.info("**********************************\n" );

        showVersions = true;
         */
    }

}
