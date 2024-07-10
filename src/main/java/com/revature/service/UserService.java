package com.revature.service;

import com.revature.entity.User;

public class UserService {

    public UserService(){

    }

    public User validateAccountInfo(User accountInfo) {

        if (checkUsernamePasswordLength(accountInfo)) {
            if (checkUsernameIsUnique(accountInfo)) {
                // return new User account from UserDao with accountInfo
                // TODO
            }
        }
        // TODO
        throw new RuntimeException("account info was not successfully validated");
    }

    public boolean checkUsernamePasswordLength(User accountInfo) {
        // TODO
        return false;
    }

    public boolean checkUsernameIsUnique(User accountInfo) {
        // TODO
        return false;
    }
}
