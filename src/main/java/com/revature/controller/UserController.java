package com.revature.controller;

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
                    // Login
                    break;
                case "2":
                    // Register
                    break;
                case "q":
                    // end user session
                    controlMap.put("continue service", "false");
            }
        } catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
