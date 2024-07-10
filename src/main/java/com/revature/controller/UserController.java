package com.revature.controller;

import com.revature.entity.User;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class UserController {

    private Scanner scanner;

    public UserController(Scanner scanner) {
        this.scanner = scanner;
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

    public void registerUser() {
        User accountInfo = getAccountInfo();
        System.out.println(accountInfo);
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
