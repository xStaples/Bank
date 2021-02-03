package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.exceptions.DatabaseConnectionException;

import org.postgresql.Driver;

public class ConnectionUtil {
    private ConnectionUtil() {
        super();
    }

    public static Connection getConnection() throws DatabaseConnectionException {
        Connection connection = null;

        try {
            DriverManager.registerDriver(new Driver());

            String url = System.getenv("db_url");
            String user = System.getenv("db_username");
            String password = System.getenv("db_password");

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Cannot Connect To Database");
        }
        return connection;
    }

}
