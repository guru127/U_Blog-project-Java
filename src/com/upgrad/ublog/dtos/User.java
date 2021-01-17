package com.upgrad.ublog.dtos;

public class User {
    private int userId;
    private String emailId;
    private String password;

    public User(int userId, String emailId, String password) {
        this.userId = userId;
        this.emailId = emailId;
        this.password = password;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
               ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setEmailId("dummy@dummy.com");
        user.setPassword("password");

        System.out.println(user);
   }
}
