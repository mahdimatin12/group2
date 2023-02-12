package com.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * SqlDB is a class that provides the functionality to connect to a SQL
 * database. It uses the properties file "db.properties" to get the database
 * connection details such as driver, url, and username. The password is fetched
 * from the environment variables.
 *
 * The class also implements a method `openConnection` to establish a connection
 * to the database.
 */
public class SqlDB {

    protected Connection connection;

    protected Connection openConnection() throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {
        // instance variable to hold the database connection
        Map<String, String> variables = System.getenv();
        String password = variables.get("dbpassword");
        /**
         * openConnection method establishes a connection to the database using
         * the connection details from the properties file and the password from
         * the environment variables. open the properties file to read the
         * connection details
         */
        InputStream propsInputStream = new FileInputStream("C:\\group2\\src\\resources\\db.properties");
        Properties properties = new Properties();
        properties.load(propsInputStream);

        // read the driver, url and username from the properties file
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String dbuser = properties.getProperty("dbuser");

        // load the driver class and create a new instance
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection(url, dbuser, password);
        propsInputStream.close();
        return connection;
    }
}
