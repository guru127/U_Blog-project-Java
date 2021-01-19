package com.upgrad.ublog.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message){
        super(message);
    }

    public static void main(String[] args) {
        try {
            throw new IncorrectPasswordException("Custom Message");
        } catch (IncorrectPasswordException e) {
            System.out.println(e.getMessage());
       }
    }
}
