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

    public void userPrompt() {

        System.out.println("What would you like to do?:");
        System.out.println("Login (type 1 + ENTER)");
        System.out.println("Register (type 2 + ENTER)");
        System.out.println("Logout (type 3 + ENTER");
        System.out.println("Quit (type q + ENTER)");

        try {
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    login();
                    break;
                case "2":
                    registerUser();
                    break;
                case "3":
                    logout();
                case "q":
                    // end user session
                    controlMap.put("continue service", "false");
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    public void login() {
        User user = userService.checkLoginAccountInfo(getAccountInfo());
        controlMap.put("user", user.getUsername());
        System.out.println("Welcome " + user.getUsername());
    }

    public void logout() {
        System.out.println("Goodbye, " + controlMap.get("user"));
        controlMap.put("continue service", "false");
        controlMap.put("user", "");
    }

    public void registerUser() {
        User accountInfo = getAccountInfo();
        User newUser = userService.validateAccountInfo(accountInfo);
        System.out.println("New account created: " + newUser);
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
