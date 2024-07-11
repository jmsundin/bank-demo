package com.revature.repository;

import com.revature.entity.User;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDao  implements UserDao {

    @Override
    public User createUser(User accountInfo) {

        String sql = "INSERT INTO users VALUES (?, ?)";

        try(Connection conn = DatabaseConnector.createConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountInfo.getUsername());
            ps.setString(2, accountInfo.getPassword());

            int result = ps.executeUpdate();
            if (result == 1) {
                return accountInfo;
            }

            throw new UserSQLException("User account could not be created, please try again.");
        } catch (SQLException e) {
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try(Connection conn = DatabaseConnector.createConnection()){
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(resultSet.next()){
                User userRecord = new User();
                userRecord.setUsername(resultSet.getString("username"));
                userRecord.setPassword(resultSet.getString("password"));
                users.add(userRecord);
            }
            return users;
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

}
