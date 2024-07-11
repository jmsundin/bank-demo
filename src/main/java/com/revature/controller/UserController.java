package com.revature.controller;

import com.revature.entity.User;
import com.revature.service.UserService;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class UserController {

    private Scanner scanner;
    private UserService userService;

    public UserController(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void userPrompt(Map<String, String> controlMap) {

        System.out.println("What would you like to do?:");
        System.out.println("Login (type 1 + ENTER)");
        System.out.println("Register (type 2 + ENTER)");
        System.out.println("Quit (type q + ENTER)");

        try {
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    getAccountInfo();
                    break;
                case "2":
                    registerUser();
                    break;
                case "q":
                    // end user session
                    controlMap.put("continue service", "false");
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    public User login() {
        return userService.checkLoginAccountInfo(getAccountInfo());
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
