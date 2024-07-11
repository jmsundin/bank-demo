package com.revature.repository;

import com.revature.entity.User;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return List.of();
    }

}
