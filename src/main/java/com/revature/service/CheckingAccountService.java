package com.revature.service;

import com.revature.controller.CheckingAccountController;
import com.revature.entity.CheckingAccount;
import com.revature.repository.CheckingAccountDao;

import java.util.List;

public class CheckingAccountService {

    private CheckingAccountDao checkingAccountDao;

    public CheckingAccountService(CheckingAccountDao checkingAccountDao) {
        this.checkingAccountDao = checkingAccountDao;
    }

    public List<CheckingAccount> getCheckingAccounts(String username) {
        return checkingAccountDao.getCheckingAccounts(username);
    }

    public CheckingAccount createCheckingAccount(CheckingAccount checkingAccount) {
        return checkingAccountDao.createCheckingAccount(checkingAccount);
    }

    public double getAccountBalance(CheckingAccount checkingAccount) {
        return checkingAccountDao.getCheckingAccountBalance(checkingAccount);
    }

    public CheckingAccount deposit(CheckingAccount checkingAccount, double amount) {
        return checkingAccountDao.deposit(checkingAccount, amount);
    }

    public CheckingAccount withdraw(CheckingAccount checkingAccount, double amount) {
        return checkingAccountDao.withdraw(checkingAccount, amount);
    }

}
