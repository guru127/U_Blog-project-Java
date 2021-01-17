package com.upgrad.ublog.exceptions;


public class PostNotFoundException extends Exception {
    public PostNotFoundException(String message){
        super(message);
    }

   public static void main(String[] args) {
        try {
            throw new PostNotFoundException("Custom Message");
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
