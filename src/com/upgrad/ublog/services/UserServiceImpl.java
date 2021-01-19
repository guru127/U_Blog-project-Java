package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dao.UserDAO;
import com.upgrad.ublog.dtos.Post;
import com.upgrad.ublog.dtos.User;
import com.upgrad.ublog.exceptions.IncorrectPasswordException;
import com.upgrad.ublog.exceptions.UserAlreadyRegisteredException;
import com.upgrad.ublog.exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements  UserService {

    private static UserServiceImpl instance;
    private DAOFactory daoFactory;
    private UserDAO userDAO;

    private UserServiceImpl() {
        daoFactory = new DAOFactory();
        userDAO = daoFactory.getUserDAO();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean login(User user) throws Exception {
        boolean flag =false;
        if (user == null) {
            throw new NullPointerException("User object was null");

        }
        User temp = null;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }
        if (temp == null) {
            throw new UserNotFoundException("No user registered with the given email address!");
        } else if (!temp.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException("Password is not correct.");
        } else {
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean register(User user) throws Exception {
        boolean flag=false;
        if (user == null) {
            throw new NullPointerException("User object was null");
        }
        User temp = null;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }
        if (temp != null) {
            flag=false;
            throw new UserAlreadyRegisteredException("A user with this email address already exists!");
        }
        else {
            userDAO.create(user);
            flag= true;
        }
        return flag;
    }

}

