package com.revature.repository;

import com.revature.entity.CheckingAccount;
import java.util.List;

public interface CheckingAccountDao {
    CheckingAccount createCheckingAccount(CheckingAccount checkingAccount);
    List<CheckingAccount> getCheckingAccounts(String username);
    double getCheckingAccountBalance(CheckingAccount checkingAccount);
    CheckingAccount deposit(CheckingAccount checkingAccount, double amount);
    CheckingAccount withdraw(CheckingAccount checkingAccount, double amount);
}
