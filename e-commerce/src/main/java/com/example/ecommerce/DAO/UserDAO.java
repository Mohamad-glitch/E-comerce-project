package com.example.ecommerce.DAO;

import java.util.List;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;

public interface UserDAO  {
    // CRUD operation
    void saveUser(User user);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUserByEmail(String email);

    void updateUserRole(String email, String role);

    Cart findUserCartByEmail(String email);

}
