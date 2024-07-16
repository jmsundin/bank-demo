package com.revature.exception;

public class RegistrationFail extends RuntimeException {
    public RegistrationFail(String message){
        super(message);
    }
}
