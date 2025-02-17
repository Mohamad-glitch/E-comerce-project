package com.example.ecommerce.service;


import com.example.ecommerce.DAO.UserDAO;
import com.example.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void createUser(Model model, User user) {

        model.getAttribute("user");


    }



}
