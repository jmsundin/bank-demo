package com.revature.repository;

import com.revature.entity.CheckingAccount;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteCheckingAccountDao implements CheckingAccountDao {

    @Override
    public CheckingAccount createCheckingAccount(CheckingAccount checkingAccount) {
        String sql = "INSERT INTO accounts VALUES (?, ?, ?)";

        try(Connection conn = DatabaseConnector.createConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, checkingAccount.getAccountNumber());
            ps.setDouble(2, 0.00);
            ps.setString(3, checkingAccount.getOwner());

            int result = ps.executeUpdate();
            if (result == 1) {
                return checkingAccount;
            }

            throw new UserSQLException("User account could not be created, please try again.");
        } catch (SQLException e) {
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public List<CheckingAccount> getCheckingAccounts(String username) {
        String sql = "SELECT * FROM accounts WHERE owner = ?";
        try(Connection conn = DatabaseConnector.createConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            List<CheckingAccount> accounts = new ArrayList<>();
            while(rs.next()){
                CheckingAccount accountRecord = new CheckingAccount();
                accountRecord.setAccountNumber(rs.getInt("account_number"));
                accountRecord.setBalance(rs.getDouble("balance"));
                accountRecord.setOwner(rs.getString("owner"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e){
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public double getCheckingAccountBalance(CheckingAccount checkingAccount) {
        String sql = "SELECT balance FROM accounts WHERE account_number = ? AND owner = ?";

        try(Connection conn = DatabaseConnector.createConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, checkingAccount.getAccountNumber());
            ps.setString(2, checkingAccount.getOwner());

            ResultSet rs = ps.executeQuery();

            double balance = 0.00;
            while(rs.next()) {
                balance = rs.getDouble("balance");
            }

            return balance;

        } catch (SQLException e){
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public CheckingAccount deposit(CheckingAccount checkingAccount, double amount) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ? AND owner = ?";

        try(Connection conn = DatabaseConnector.createConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, checkingAccount.getAccountNumber());
            ps.setString(3, checkingAccount.getOwner());

            int rs = ps.executeUpdate();
            if (rs == 1) {
                checkingAccount.setBalance(amount);
                return checkingAccount;
            }
            return null;
        } catch (SQLException e){
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public CheckingAccount withdraw(CheckingAccount checkingAccount, double amount) {
        double balance = getCheckingAccountBalance(checkingAccount);

        if (balance - amount < 0.00) {
            return null;
        }

        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ? AND owner = ?";
        balance -= amount;
        checkingAccount.setBalance(balance);

        try(Connection conn = DatabaseConnector.createConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setInt(2, checkingAccount.getAccountNumber());
            ps.setString(3, checkingAccount.getOwner());

            int rs = ps.executeUpdate();
            if (rs == 1) return checkingAccount;
            return null;
        } catch (SQLException e){
            throw new UserSQLException(e.getMessage());
        }
    }

    @Override
    public void deleteCheckingAccount(CheckingAccount checkingAccount) {
        String sql = "DELETE FROM accounts WHERE account_number = ? AND owner = ?";

        try(Connection conn = DatabaseConnector.createConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, checkingAccount.getAccountNumber());
            ps.setString(2, checkingAccount.getOwner());

            int rs = ps.executeUpdate();
            if (rs != 1) {
                throw new UserSQLException("Account could not be deleted. Please try again.");
            }
        } catch (SQLException e){
            throw new UserSQLException(e.getMessage());
        }
    }
}
