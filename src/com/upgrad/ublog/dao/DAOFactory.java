package com.upgrad.ublog.dao;

import com.upgrad.ublog.dtos.User;

public class DAOFactory {
    public PostDAO getPostDAO(){
        return PostDAOImpl.getInstance();
    }
    public  UserDAO getUserDAO(){
        return UserDAOImpl.getInstance();
    }
}
