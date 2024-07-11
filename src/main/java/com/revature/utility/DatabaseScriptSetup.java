package com.revature.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DatabaseScriptSetup {
    public static void main(String[] args) {
        String path = "src/main/resources/bank-demo-db-setup-script.sql";
        Path sqlPath = Paths.get(path);
        try {
            try (Connection conn = DatabaseConnector.createConnection();
            Stream<String> lines = Files.lines(sqlPath)
            ) {
                conn.setAutoCommit(false);

                StringBuilder sqlBuilder = new StringBuilder();
                lines.forEach(line -> sqlBuilder.append(line));
                String sql = sqlBuilder.toString();
                String[] statements = sql.split(";");
                for (String statement : statements) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(statement);
                }
                conn.commit();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
