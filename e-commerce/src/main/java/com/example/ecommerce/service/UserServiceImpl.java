package com.example.ecommerce.service;


import com.example.ecommerce.DAO.UserDAO;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }


    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userDAO.deleteUserByEmail(email);
    }

    @Transactional
    public void updateUserRole(String email, String role) {
        userDAO.updateUserRole(email, role);
    }

    @Transactional
    public Cart findUserCartByEmail(String email){
        return userDAO.findUserCartByEmail(email);
    }

}
