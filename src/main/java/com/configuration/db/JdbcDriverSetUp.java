package com.configuration.db;

import com.configuration.reporting.TestLogger;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDriverSetUp {

    private static final Logger LOGGER = TestLogger.getLogger(JdbcDriverSetUp.class);
    private Connection connection = null;


    public void dbDriverSetUp()

    {
        LOGGER.info("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.info("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        LOGGER.info("MySQL JDBC Driver Registered!");

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/mimesis", "root", "root");

        } catch (SQLException e) {
            LOGGER.info("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            LOGGER.info("You made it, take control your database now!");
        } else {
            LOGGER.info("Failed to make connection!");
        }
    }

    public Connection getConnectionDb(){
        return connection;
    }
}
