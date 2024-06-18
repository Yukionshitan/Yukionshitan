package com.example.demo1.controller;

import com.example.demo1.dao.UserDAO;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public Boolean registerUser(String userType, String passWord, String userName) {
        return userDAO.saveUser(userType, passWord, userName);
    }

    public Boolean login(String userType, String passWord, String userName) {
        return userDAO.login(userName, passWord, userType);
    }
}
