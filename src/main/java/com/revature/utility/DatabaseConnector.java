package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection createConnection() throws SQLException {
        String database = "jdbc:sqlite:src/main/resources/band-demo.db";
        return DriverManager.getConnection(database);
    }

    public static void main(String[] args) {
        try {
            try(Connection connection = createConnection()) {
                System.out.println(connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
