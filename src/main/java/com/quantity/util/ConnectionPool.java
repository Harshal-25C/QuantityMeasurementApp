package com.quantity.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    public static Connection getConnection() {

        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");

            String url = ApplicationConfig.getProperty("db.url");
            String username = ApplicationConfig.getProperty("db.username");
            String password = ApplicationConfig.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            throw new RuntimeException("Database connection failed", e);

        }

    }

}