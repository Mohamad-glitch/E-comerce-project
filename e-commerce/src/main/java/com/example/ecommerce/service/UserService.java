package com.example.ecommerce.service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUserByEmail(String email);

    void updateUserRole(String email, String role);

    Cart findUserCartByEmail(String email);

}
