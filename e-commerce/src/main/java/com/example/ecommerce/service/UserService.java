package com.example.ecommerce.service;


import com.example.ecommerce.DAO.UserDAO;
import com.example.ecommerce.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    UserDAO userDAO;


    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void createUser(User user) {
        user.setRole("USER");
        user.setPassword("{bcrypt}" +  new BCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Transactional
    public User findUserByEmail(String email) {
        return   userDAO.findUserByEmail(email);
    }


}
