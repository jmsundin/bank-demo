package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.repository.UserDao;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User validateAccountInfo(User accountInfo) {

        if (checkUsernamePasswordLength(accountInfo)) {
            if (checkUsernameIsUnique(accountInfo)) {
                // return new User account from UserDao with accountInfo
                return userDao.createUser(accountInfo);
            }
        }
        // TODO: handle this exception as a custom exception
        throw new RuntimeException("account info was not successfully validated");
    }

    public User checkLoginAccountInfo(User accountInfo){
        for (User user : userDao.getAllUsers()){
            // if username and password match return the credentials
            boolean usernameMatches = user.getUsername().equals(accountInfo.getUsername());
            boolean passwordMatches = user.getPassword().equals(accountInfo.getPassword());
            if (usernameMatches && passwordMatches){
                return accountInfo;
            }
        }
        // this exception holds our failure message for the user if their credentials are invalid
        throw new LoginFail("Credentials are invalid: please try again");
    }

    public boolean checkUsernamePasswordLength(User accountInfo) {
        boolean usernameIsValid = accountInfo.getUsername().length() <= 30;
        boolean passwordIsValid = accountInfo.getPassword().length() <= 30;
        return usernameIsValid && passwordIsValid;
    }

    public boolean checkUsernameIsUnique(User accountInfo) {
        boolean usernameIsUnique = true;
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            if(accountInfo.getUsername().equals(user.getUsername())){
                usernameIsUnique = false;
                break;
            }
        }
        return usernameIsUnique;
    }
}
