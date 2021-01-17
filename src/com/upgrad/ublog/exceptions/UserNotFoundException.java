package com.upgrad.ublog.exceptions;

public class UserNotFoundException extends  Exception{
    public UserNotFoundException( String message){
        super( message);
    }

    public static void main(String[] args) {
        try {
            throw new UserNotFoundException("Custom Message");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
