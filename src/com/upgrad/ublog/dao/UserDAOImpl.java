package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    private UserDAOImpl(){}

    public static UserDAOImpl getInstance() {
        if (instance==null){
            instance=new UserDAOImpl();
        }
        return instance;
    }

    @Override

    public User create(User user) throws SQLException {
        Connection connection = Database.getInstance();

        String sql = "INSERT INTO USER (userId, emailId, password) VALUES (?,?,?)";
     try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, user.getUserId());
         preparedStatement.setString(2, user.getEmailId());
         preparedStatement.setString(3, user.getPassword());

         preparedStatement.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     }
        return user;
    }
    @Override
    public User findByEmailId(String emailId) throws SQLException {

        Connection connection = Database.getInstance();
        String sql = "SELECT * FROM user WHERE emailId = '" + emailId + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setEmailId(resultSet.getString("emailId"));
                user.setPassword(resultSet.getString("password"));
                user.setUserId(resultSet.getInt("userId"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User temp = new User();
            temp.setUserId(1);
            temp.setEmailId("temp@temp.temp");
            temp.setPassword("temp");
            userDAO.create(temp);
            System.out.println(userDAO.findByEmailId("guru@gmail.com"));
        } catch (Exception e) {
            System.out.println("FAILED");
        }

        /**
//         * Following should be the desired output of the main method.
//         * User{userId=11, emailId='temp@temp.temp', password='temp'}
//         */
    }
}
