package com.revature.controller;

import com.revature.AppState;
import com.revature.entity.User;
import com.revature.service.UserService;

import java.util.Map;
import java.util.Scanner;

public class UserController {

    private Scanner scanner;
    private UserService userService;
    private Map<String, String> controlMap;

    public UserController(Scanner scanner, UserService userService, Map<String, String> controlMap) {
        this.scanner = scanner;
        this.userService = userService;
        this.controlMap = controlMap;
    }

    public AppState landingPrompt() {

        System.out.println("Login (type 1 + ENTER)");
        System.out.println("Register (type 2 + ENTER)");
        System.out.println("Quit (type q + ENTER)");

        try {
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    return login();
                case "2":
                    return registerUser();
                case "q":
                    // end user session
                    controlMap.put("continue service", "false");
                    return AppState.QUIT;
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return AppState.LANDING_PROMPT;
    }

    public AppState login() {
        User user = userService.checkLoginAccountInfo(getAccountInfo());
        controlMap.put("user", user.getUsername());
        System.out.println("Welcome " + user.getUsername());
        return AppState.MAIN_MENU;
    }

    public AppState registerUser() {
        User accountInfo = getAccountInfo();
        User newUser = userService.validateAccountInfo(accountInfo);
        controlMap.put("user", newUser.getUsername());
        System.out.println("New account created: " + newUser);
        return AppState.MAIN_MENU;
    }

    public User getAccountInfo() {
        String username;
        String password;

        System.out.println("Username: ");
        username = scanner.nextLine();
        System.out.println("Password: ");
        password = scanner.nextLine();

        return new User(username, password);
    }

}
