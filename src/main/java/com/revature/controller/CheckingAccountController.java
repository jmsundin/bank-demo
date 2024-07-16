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

    public AppState showMainMenu() {
        System.out.println("View Accounts (type 1 + ENTER)");
        System.out.println("New Account (type 2 + ENTER)");
        System.out.println("Logout (type 3 + ENTER");

        try {
            String input = scanner.next() + scanner.nextLine();

            switch (input) {
                case "1":
                    return getAccounts();
                case "2":
                    return createCheckingAccount();
                case "3":
                    return logout();
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return AppState.LANDING_PROMPT;
    }

    public AppState showAccountOptions() {

        System.out.println("Deposit (type 1 + ENTER)");
        System.out.println("Withdraw (type 2 + ENTER)");
        System.out.println("View Accounts (type 3 + ENTER");
        System.out.println("Main Menu (type 4 + ENTER");
        System.out.println("Logout (type 5 + ENTER");
        System.out.println("Close Account (type CLOSE ACCOUNT + ENTER");

        
        try {
            String input = scanner.next() + scanner.nextLine();

            switch (input) {
                case "1":
                    return deposit(this.checkingAccount);
                case "2":
                    return withdraw(this.checkingAccount);
                case "3":
                    return getAccounts();
                case "4":
                    return AppState.MAIN_MENU;
                case "5":
                    return logout();
                case "CLOSE ACCOUNT":
                    return closeAccount();
                default:
                    System.out.println("Invalid selection. Please try again.");
                    return showAccountOptions();
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return AppState.MAIN_MENU;
    }

    public AppState getAccounts() {
        List<CheckingAccount> checkingAccounts = checkingAccountService.getCheckingAccounts(controlMap.get("user"));
        if (checkingAccounts.isEmpty()) {
            System.out.println("No accounts found.");
            return AppState.MAIN_MENU;
        }
        return showAccounts(checkingAccounts);
    }


    private AppState showAccounts(List<CheckingAccount> checkingAccounts) {
        Map<Integer, CheckingAccount> indexToCheckingAccount = new HashMap<>();
        ListIterator<CheckingAccount> iterator = checkingAccounts.listIterator();

        while(iterator.hasNext()) {
            int index = iterator.nextIndex();
            CheckingAccount account = iterator.next();
            indexToCheckingAccount.put(index, account);
            System.out.printf("Account: %d (type %d + ENTER to view account)%n", account.getAccountNumber(), index + 1);
        }
        System.out.println("(type 0 + ENTER to go back to the Main Menu)");
        System.out.println("Select an account: ");

        String input = scanner.next() + scanner.nextLine();
        try {
            int selectedIndex = Integer.parseInt(input) - 1;
            if (indexToCheckingAccount.containsKey(selectedIndex)) {
                CheckingAccount selectedAccount = indexToCheckingAccount.get(selectedIndex);
                setCurrentCheckingAccount(selectedAccount);
                showAccountInfo(selectedAccount);
                return AppState.ACCOUNT_MENU;
            } else if (selectedIndex == -1) {
                return AppState.MAIN_MENU;
            } else {
                System.out.println("Invalid selection. Please try again.");
                showAccounts(checkingAccounts);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return AppState.MAIN_MENU;
    }

    private void showAccountInfo(CheckingAccount checkingAccount) {
        System.out.printf("Account: %d, Balance: $%.2f\n", checkingAccount.getAccountNumber(), checkingAccount.getBalance());
    }

    private AppState createCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setAccountNumber((int) (Math.random() * 2_000_000_000));
        checkingAccount.setOwner(controlMap.get("user"));
        CheckingAccount newCheckingAccount = checkingAccountService.createCheckingAccount(checkingAccount);
        System.out.println("Creating new checking account...");
        if (newCheckingAccount == null) System.out.println("Unsuccessful: creating new checking account.");
        else System.out.println("Successfully created account: " + newCheckingAccount.getAccountNumber());
        this.checkingAccount = newCheckingAccount;
        showAccountInfo(newCheckingAccount);
        return AppState.ACCOUNT_MENU;
    }

    private AppState deposit(CheckingAccount checkingAccount) {
        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0.00) {
            System.out.println("Please enter a positive number.");
            deposit(checkingAccount);
        }
        System.out.println("Depositing funds...");
        double currentBalance = checkingAccountService.getAccountBalance(checkingAccount);
        double newBalance = amount + currentBalance;
        CheckingAccount updatedRecord = checkingAccountService.deposit(checkingAccount, newBalance);
        this.checkingAccount = updatedRecord;
        showAccountInfo(updatedRecord);
        return AppState.ACCOUNT_MENU;
    }

    private AppState withdraw(CheckingAccount checkingAccount) {
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        CheckingAccount updatedRecord = checkingAccountService.withdraw(checkingAccount, amount);
        this.checkingAccount = updatedRecord;
        showAccountInfo(updatedRecord);
        return AppState.ACCOUNT_MENU;
    }

    public void setCurrentCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public CheckingAccount getCurrentCheckingAccount() {
        return this.checkingAccount;
    }

    private AppState closeAccount() {
        System.out.println("Are you sure you want to close this account? (type YES + ENTER to confirm)");
        String input = scanner.next() + scanner.nextLine();
        if (input.equals("YES")) {
            checkingAccountService.closeAccount(this.checkingAccount);
            System.out.println("Account closed.");
        } else {
            System.out.println("Account not closed.");
        }
        return AppState.MAIN_MENU;
    }

    public AppState logout() {
        System.out.println("Goodbye, " + controlMap.get("user"));
        controlMap.put("user", "");
        return AppState.QUIT;
    }
}
