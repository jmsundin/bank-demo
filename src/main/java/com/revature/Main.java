package com.revature;

import com.revature.controller.UserController;
import com.revature.entity.User;
import com.revature.repository.SqliteUserDao;
import com.revature.repository.UserDao;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try (Scanner scanner = new Scanner(System.in)) {
            UserDao userDao = new SqliteUserDao();
            UserService userService = new UserService(userDao);
            Map<String, String> controlMap = new HashMap<>();
            controlMap.put("continue service", "true");

            UserController controller = new UserController(scanner, userService, controlMap);

            while(Boolean.parseBoolean(controlMap.get("continue service"))) {
                controller.userPrompt();
            }
        }
    }
}