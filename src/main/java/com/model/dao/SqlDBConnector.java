package com.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/* 
*SqlDBConnector class, which extends the SqlDB class and is used for connecting to a SQL database.
*class has a single constructor which calls the openConnection method from its parent class to establish a connection to the database.
 */
public class SqlDBConnector extends SqlDB {

    private Connection connection;

    public SqlDBConnector() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

        this.connection = super.openConnection();
    }

    //connection: returns the established connection.
    public Connection connection() {
        return this.connection;
    }

    //closeConnection: closes the established connection.
    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
