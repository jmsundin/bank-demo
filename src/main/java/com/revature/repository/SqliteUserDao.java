package com.revature.repository;

import com.revature.entity.User;
import com.revature.utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SqliteUserDao  implements UserDao {

    @Override
    public User createUser(User accountInfo) {

        String sql = "INSERT INTO users VALUES (?, ?)";

        try(Connection conn = DatabaseConnector.createConnection()){

        } catch (SQLException e) {
            throw new UserSQLException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

}
