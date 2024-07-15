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
            controlMap.put("continue service", "true");

            UserController controller = new UserController(scanner, userService, controlMap);

            while(Boolean.parseBoolean(controlMap.get("continue service"))) {
                controller.userPrompt();
            }
        }
    }
}