package com.revature;

import com.revature.controller.CheckingAccountController;
import com.revature.controller.UserController;
import com.revature.repository.CheckingAccountDao;
import com.revature.repository.SqliteCheckingAccountDao;
import com.revature.repository.SqliteUserDao;
import com.revature.repository.UserDao;
import com.revature.service.CheckingAccountService;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            UserDao userDao = new SqliteUserDao();
            UserService userService = new UserService(userDao);

            CheckingAccountDao checkingAccountDao = new SqliteCheckingAccountDao();
            CheckingAccountService checkingAccountService = new CheckingAccountService(checkingAccountDao);

            Map<String, String> controlMap = new HashMap<>();

            UserController controller = new UserController(scanner, userService, controlMap);
            CheckingAccountController checkingAccountController = new CheckingAccountController(scanner,
                    checkingAccountService, controlMap);

            AppState appState = AppState.LANDING_PROMPT;

            while (appState != AppState.QUIT) {
                switch (appState) {
                    case LANDING_PROMPT:
                        appState = controller.landingPrompt();
                        break;
                    case LOGIN:
                        appState = controller.login();
                        break;
                    case REGISTER:
                        appState = controller.registerUser();
                        break;
                    case MAIN_MENU:
                        appState = checkingAccountController.showMainMenu();
                        break;
                    case ACCOUNTS_LIST:
                        appState = checkingAccountController.getAccounts();
                        break;
                    case ACCOUNT_MENU:
                        appState = checkingAccountController.showAccountOptions();
                        break;
                    default:
                        // appState = controller.userPrompt();
                        break;
                }
            }
        }
    }
}