package com.upgrad.ublog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private Database (){}
    public static Connection getInstance(){
        if (connection==null){
            return getConnection();
        }
        else return connection;
    }

    private static Connection getConnection () {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/ublog";
            String username = "root";
            String password = "guru1996";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL Driver not found.");
            } catch (SQLException e) {
                System.out.println("SQL exception ");
            }
        }
        return connection;
    }
    public static void main(String[] args) throws SQLException {
        try {
            Database.getConnection();
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Not Connected");
        }
    }
}
