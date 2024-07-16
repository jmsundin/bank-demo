package com.revature.controller;

import com.revature.AppState;
import com.revature.entity.CheckingAccount;
import com.revature.service.CheckingAccountService;

import java.util.*;

public class CheckingAccountController {

    private Scanner scanner;
    private Map<String, String> controlMap;
    private CheckingAccount checkingAccount;
    private CheckingAccountService checkingAccountService;

    public CheckingAccountController(Scanner scanner, CheckingAccountService checkingAccountService,
                                     Map<String, String> controlMap) {
        this.scanner = scanner;
        this.checkingAccountService = checkingAccountService;
        this.controlMap = controlMap;
        this.checkingAccount = null;
    }

    public void showMenu() {
        System.out.println("Accounts (type 1 + ENTER)");
        System.out.println("New Account (type 2 + ENTER)");
        System.out.println("Logout (type 3 + ENTER");
        System.out.println("Quit (type q + ENTER)");

        try {
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    getAccounts();
                    break;
                case "2":
                    createAccount();
                    break;
                case "3":
                    logout();
                    break;
                case "q":
                    // end user session
                    controlMap.put("continue service", "false");
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    public void getAccounts() {

    }

    public void createAccount() {

    }

    public void logout() {
        System.out.println("Goodbye, " + controlMap.get("user"));
        controlMap.put("continue service", "false");
        controlMap.put("user", "");
    }
}
