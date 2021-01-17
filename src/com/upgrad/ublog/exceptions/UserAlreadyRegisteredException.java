package com.upgrad.ublog.exceptions;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException (String message){
        super(message);
    }
    public static void main(String[] args) {
        try {
            throw new UserAlreadyRegisteredException("Custom Message");
        } catch (UserAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }
}
